package com.example.user.placeapp.Maps;

import com.example.user.placeapp.POJO.Nearby;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NearbyPlaces {
    //"maps/api/place/nearbysearch/json?location={lat},{lng}&radius=1500&type=restaurant&key=AIzaSyDqjrcHNDdH52OgRJEGoUl4j5iiCmNg9GY"
    //@GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
    //    Call<Example> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);
    @GET("maps/api/place/nearbysearch/json?key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
    Call<Nearby> getNearbyPlaces(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type
            //@Query("key") String google_api_key
    );
}
