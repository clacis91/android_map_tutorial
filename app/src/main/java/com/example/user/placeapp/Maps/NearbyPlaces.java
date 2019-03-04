package com.example.user.placeapp.Maps;

import com.example.user.placeapp.BuildConfig;
import com.example.user.placeapp.POJO.Nearby;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NearbyPlaces {
    //String google_api_key = BuildConfig.ApiKey;
    //@GET("maps/api/place/nearbysearch/json?key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
    @GET("maps/api/place/nearbysearch/json")
    Call<Nearby> getNearbyPlaces(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type,
            @Query("key") String google_api_key
    );
}
