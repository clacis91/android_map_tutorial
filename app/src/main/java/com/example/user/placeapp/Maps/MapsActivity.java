package com.example.user.placeapp.Maps;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.placeapp.POJO.Nearby;
import com.example.user.placeapp.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private PlacesClient placesClient;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private LatLng curPos;
    private HashMap<Marker, String> placeMarkers;
    private ImageView photoView;
    //private ArrayList<Marker> placeMarkers;
    //private ArrayList<HashMap<Marker, String>> placeMarkers;
    //private HashMap<String, Marker> placeMarkers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle       savedInstanceState) {
        return inflater.inflate(R.layout.activity_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        photoView = getActivity().findViewById(R.id.placePhoto);

        Places.initialize(getContext(), "AIzaSyDqjrcHNDdH52OgRJEGoUl4j5iiCmNg9GY");
        //Places.initialize(this.getContext(), "AIzaSyDqjrcHNDdH52OgRJEGoUl4j5iiCmNg9GY");
        placesClient = Places.createClient(this.getContext());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        placeMarkers = new HashMap<>();

        // For showing a move to my location button
        // mMap.setMyLocationEnabled(true);

        // For dropping a marker at a point on the Map
        //LatLng sydney = new LatLng(-34, 151);
        LatLng sydney = new LatLng(37.576208, 126.976818);
        //Marker marker = new Marker();
        mMap.addMarker(new MarkerOptions().position(sydney).draggable(true));
        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng newPos = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
                curPos = newPos;
                CameraPosition cameraPosition = new CameraPosition.Builder().target(newPos).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                for(Marker m : placeMarkers.keySet()) {
                    m.remove();
                }
                placeMarkers.clear();
                getNearbyResponse("museum");
            }
        });

        mMap.setOnMarkerClickListener(this);
    }

    private void getNearbyResponse(String type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NearbyPlaces nearbyPlaces = retrofit.create(NearbyPlaces.class);

        Call<Nearby> call = nearbyPlaces.getNearbyPlaces(String.valueOf(curPos.latitude) + "," + String.valueOf(curPos.longitude), 1500, type);

        call.enqueue(new Callback<Nearby>() {
            @Override
            public void onResponse(Call<Nearby> call, Response<Nearby> response) {
                if (!response.isSuccessful()) {
                    Log.d("response_fail", response.toString());
                    return;
                }

                try {
                    // 이 method는 분리
                    // This loop will go through all the results and add marker on each location.
                    Log.d("onResponse", response.body().getResults().toString());
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        Double lat = response.body().getResults().get(i).getGeometry().getLocation().getLat();
                        Double lng = response.body().getResults().get(i).getGeometry().getLocation().getLng();
                        String markerPlaceId = response.body().getResults().get(i).getPlaceId();
                        Log.d("searched_markers", markerPlaceId);

                        LatLng latlng = new LatLng(lat, lng);

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latlng);
                        // Adding Title to the Marker
                        //markerOptions.title(placeName + " : " + vicinity);
                        Marker marker = mMap.addMarker(markerOptions);
                        placeMarkers.put(marker, markerPlaceId);
                    }
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
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
    public boolean onMarkerClick(Marker marker) {
        String clicked_marker_id = marker.getId();
        for(Marker m : placeMarkers.keySet()){
            if(clicked_marker_id.equals(m.getId())) {
                fetchPlace(placeMarkers.get(m));
            }
        }

        return true;
    }

    private void fetchPlace(String placeId) {
        EditText curPosEditText = getActivity().findViewById(R.id.curPos);
        curPosEditText.setText(placeId);

        List<Place.Field> placeFields = Arrays.asList(Place.Field.PHOTO_METADATAS);
        //FetchPlaceRequest request = FetchPlaceRequest.newInstance(placeId, placeFields);
        FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(placeId, placeFields).build();
        Task<FetchPlaceResponse> placeTask = placesClient.fetchPlace(placeRequest);

        Log.d("fetchPlace: ", "SUCCESS");

        placeTask.addOnSuccessListener(
                (response) -> {
                    //responseView.setText(StringUtil.stringify(response, isDisplayRawResultsChecked()));
                    //if (isFetchPhotoChecked) {
                    Log.d("fetchPlace_res: ", "SUCCESS");
                    fetchPhoto(response.getPlace());
                    //}
                });

        placeTask.addOnFailureListener(
                (exception) -> {
                    Log.d("fetchPlace_res: ", "FAIL");
                    exception.printStackTrace();
                    //responseView.setText(exception.getMessage());
                });
    }

    private void fetchPhoto(Place place) {
        // Get the photo metadata.
        PhotoMetadata photoMetadata = place.getPhotoMetadatas().get(0);

        // Get the attribution text.
        String attributions = photoMetadata.getAttributions();

        // Create a FetchPhotoRequest.
        FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata)
                .setMaxWidth(300) // Optional.
                .setMaxHeight(300) // Optional.
                .build();

        placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
            Log.d("fetchphoto: ", "SUCCESS");
            Bitmap bitmap = fetchPhotoResponse.getBitmap();
            photoView.setImageBitmap(bitmap);
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                int statusCode = apiException.getStatusCode();
                // Handle error with given status code.
                Log.d("fetchphoto", "Place not found: " + exception.getMessage());
            }
        });

        /*
        Task<FetchPhotoResponse> photoTask = placesClient.fetchPhoto(photoRequestBuilder.build());

        photoTask.addOnSuccessListener(
                response -> {
                    photoView.setImageBitmap(response.getBitmap());
                    StringUtil.prepend(responseView, StringUtil.stringify(response.getBitmap()));
                });

        photoTask.addOnFailureListener(
                exception -> {
                    exception.printStackTrace();
                    StringUtil.prepend(responseView, "Photo: " + exception.getMessage());
                });

        photoTask.addOnCompleteListener(response -> setLoading(false));
        */
    }
}
