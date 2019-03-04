package com.example.user.placeapp.Maps.model;

import android.util.Log;

import com.example.user.placeapp.Maps.GoogleMapContract;
import com.example.user.placeapp.Maps.NearbyPlaces;
import com.example.user.placeapp.Maps.RetrofitClient;
import com.example.user.placeapp.POJO.Nearby;
import com.example.user.placeapp.POJO.NearbyResult;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsModel implements GoogleMapContract.Model {

    @Override
    public void callNearby(OnFinishedListener onFinishedListener, LatLng curPos, String type, String googleApiKey) {
        NearbyPlaces nearbyPlaces = RetrofitClient.getInstance().getRetrofit().create(NearbyPlaces.class);
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
