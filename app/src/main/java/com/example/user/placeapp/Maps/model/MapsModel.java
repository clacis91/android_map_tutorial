package com.example.user.placeapp.Maps.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.placeapp.Maps.GISclient;
import com.example.user.placeapp.Maps.GoogleMapContract;
import com.example.user.placeapp.Maps.NearbyPlaces;
import com.example.user.placeapp.Maps.RetrofitClient;
import com.example.user.placeapp.POJO.Nearby;
import com.example.user.placeapp.kPOJO.GeocodeResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;
import static java.nio.charset.StandardCharsets.*;

public class MapsModel implements GoogleMapContract.Model {

    @Override
    public void callGeocode() {

        URL baseurl = null;
        try {
            baseurl = new URL("https://gis.kt.com/search/v1.0/");
        } catch (MalformedURLException e) {
            Log.d("geocode_test", "URL error");
            e.printStackTrace();
        }

        GISclient giSclient = RetrofitClient.getInstance().getRetrofit(baseurl).create(GISclient.class);
        //Call<GeocodeResult> call = geocode.getNearbyPlaces(String.valueOf(curPos.latitude) + "," + String.valueOf(curPos.longitude), 1000, type, googleApiKey);
        //String kt_juso = new String("서울시 서초구 태봉로 151".getBytes(), "UTF-8");
        String kt_string = "Seocho-gu Seoul Republic of Korea";
        //byte[] ptext = kt_string.getBytes(ISO_8859_1);
        //String kt_juso = new String(ptext, UTF_8);
        Call<GeocodeResult> call = giSclient.getGeocodeResult(37.4713571,127.0271674);

        call.enqueue(new Callback<GeocodeResult>() {
            @Override
            public void onResponse(Call<GeocodeResult> call, Response<GeocodeResult> response) {
                try {
                    Log.d("geocode_test", response.toString());
                } catch (Exception e) {
                    Log.d("geocode_test", "There is an error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<GeocodeResult> call, Throwable t) {
                Log.d("geocode_fail", t.toString());
            }
        });
    }

    @Override
    public void callNearby(OnFinishedListener onFinishedListener, LatLng curPos, String type, String googleApiKey) {
        URL baseurl = null;
        try {
            baseurl = new URL("https://maps.googleapis.com/");
        } catch (MalformedURLException e) {
            Log.d("callNearby", "URL error");
            e.printStackTrace();
        }

        NearbyPlaces nearbyPlaces = RetrofitClient.getInstance().getRetrofit(baseurl).create(NearbyPlaces.class);
        Call<Nearby> call = nearbyPlaces.getNearbyPlaces(String.valueOf(curPos.latitude) + "," + String.valueOf(curPos.longitude), 1000, type, googleApiKey);

        call.enqueue(new Callback<Nearby>() {
            @Override
            public void onResponse(Call<Nearby> call, Response<Nearby> response) {
                if (!response.isSuccessful()) {
                    Log.d("response_fail", response.toString());
                    return;
                }

                try {
                    onFinishedListener.onNearbyFinished(response);
                } catch (Exception e) {
                    Log.d("callNearby-onResponse", "There is an error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Nearby> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    @Override
    public void callPlaceInfo(OnFinishedListener onFinishedListener, PlacesClient placesClient, String placeId) {
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.PHOTO_METADATAS);
        FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(placeId, placeFields).build();
        Task<FetchPlaceResponse> placeTask = placesClient.fetchPlace(placeRequest);

        placeTask.addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
            @Override
            public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                Place res_place = fetchPlaceResponse.getPlace();
                List<PhotoMetadata> photoMetadatas = res_place.getPhotoMetadatas();

                if (photoMetadatas != null && !photoMetadatas.isEmpty()) {
                    callPhoto(onFinishedListener, placesClient, res_place);
                }
                else {
                    onFinishedListener.onPlaceFinished(res_place, null);
                }
            }
        });

        placeTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("fetchPlace_res: ", "FAIL");
                e.printStackTrace();
            }
        });
    }

    private void callPhoto(OnFinishedListener onFinishedListener, PlacesClient placesClient, Place place) {
        // Get the photo metadata.
        PhotoMetadata photoMetadata = place.getPhotoMetadatas().get(0);

        // Get the attribution text.
        String attributions = photoMetadata.getAttributions();

        // Create a FetchPhotoRequest.
        FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata)
                .setMaxWidth(800) // Optional.
                .setMaxHeight(600) // Optional.
                .build();

        placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
            Bitmap bitmap = fetchPhotoResponse.getBitmap();
            onFinishedListener.onPlaceFinished(place, bitmap);
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                int statusCode = apiException.getStatusCode();
                // Handle error with given status code.
                Log.d("fetchphoto", "Place not found: " + exception.getMessage());
            }
        });
    }


}
