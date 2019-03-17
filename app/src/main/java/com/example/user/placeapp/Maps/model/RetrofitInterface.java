package com.example.user.placeapp.Maps.model;

import com.example.user.placeapp.POJO.sPlace;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("places/getMyPlacesLists")
    Call<sPlace> postPlaceList();
}