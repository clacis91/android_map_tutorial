package com.example.user.placeapp.Maps.presenter;

import android.util.Log;

import com.example.user.placeapp.Maps.GoogleMapTutorial;
import com.example.user.placeapp.Maps.NearbyPlaces;
import com.example.user.placeapp.Maps.RetrofitClient;
import com.example.user.placeapp.POJO.Nearby;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements GoogleMapTutorial.GoogleMapPresenter {


    GoogleMapTutorial.GoogleMapView view;
    public MainPresenter(GoogleMapTutorial.GoogleMapView view) {
        this.view = view;
    }

    @Override
    public void getNearby(LatLng curPos, String type, String googleApiKey) {

        NearbyPlaces nearbyPlaces = RetrofitClient.getInstance().getRetrofit().create(NearbyPlaces.class);

        Call<Nearby> call = nearbyPlaces.getNearbyPlaces(String.valueOf(curPos.latitude) + "," + String.valueOf(curPos.longitude), 1000, type, googleApiKey);


        ///Model

        call.enqueue(new Callback<Nearby>() {
            @Override
            public void onResponse(Call<Nearby> call, Response<Nearby> response) {
                if (!response.isSuccessful()) {
                    Log.d("response_fail", response.toString());
                    return;
                }

                try {
                    // 이 method는 분리

                    //List<LatLng> list = new ArrayList<>();
                    HashMap<LatLng,String> map = new HashMap<>();
                    // This loop will go through all the results and add marker on each location.
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        Double lat = response.body().getResults().get(i).getGeometry().getLocation().getLat();
                        Double lng = response.body().getResults().get(i).getGeometry().getLocation().getLng();
                        String markerPlaceId = response.body().getResults().get(i).getPlaceId();

                        LatLng latlng = new LatLng(lat, lng);

                        map.put(latlng,markerPlaceId);


                        view.drawMarker(map);
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
}
