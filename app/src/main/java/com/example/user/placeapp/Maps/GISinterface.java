package com.example.user.placeapp.Maps;

import com.example.user.placeapp.kPOJO.Geocode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GISinterface {
    //@GET("maps/api/place/nearbysearch/json")
    //https://gis.kt.com/search/v1.0/pois
    //https://gis.kt.com/search/v1.0/utilities/geocode

    @GET("utilities/geocode")
    Call<Geocode> getGeocode(
            @Query("addressText") String addressText
            //@Query("point.lat") Double latitude,
            //@Query("point.lng") Double longitude
    );
}
