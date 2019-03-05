package com.example.user.placeapp.Maps;

import com.example.user.placeapp.kPOJO.GeocodeResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GISclient {
    //@GET("maps/api/place/nearbysearch/json")
    //https://gis.kt.com/search/v1.0/pois
    //https://gis.kt.com/search/v1.0/utilities/geocode

    @Headers({
        "Authorization: Bearer eb142d9027f84d51a4a20df8490e44bcf6fc7ef4dea64cae96a7fca282ebd8cc02764651"
    })
    @GET("utilities/geocode")
    Call<GeocodeResult> getGeocodeResult(
            //@Query("addressText") String addressText
            @Query("point.lat") Double latitude,
            @Query("point.lng") Double longitude
    );
}
