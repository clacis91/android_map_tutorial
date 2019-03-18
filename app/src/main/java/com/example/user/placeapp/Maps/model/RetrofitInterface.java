package com.example.user.placeapp.Maps.model;

import com.example.user.placeapp.POJO.sAccess;
import com.example.user.placeapp.POJO.sPlace;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    public class signCheckRequest {
        private String accessToken;
        public signCheckRequest(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @POST("user/signInOrSignUp")
    Call<sAccess> getSignCheck(@Body signCheckRequest body);

    @GET("places/getMyPlacesLists")
    Call<ArrayList<sPlace>> getMyPlacesLists(@Query("fbId") String fbId);
}