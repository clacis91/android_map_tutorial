package com.example.user.placeapp.Maps.model;

import com.example.user.placeapp.POJO.sAccess;
import com.example.user.placeapp.POJO.sPlace;
import com.example.user.placeapp.POJO.sPlaceReview;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @Multipart
    @POST("places/addPlaceInfo")
    Call<sPlaceReview> addPhoto(@Query("poiId") String poiId,
                                @Part MultipartBody.Part file);

    @GET("places/getMyPlacesLists")
    Call<ArrayList<sPlace>> getMyPlacesLists(@Query("fbId") String fbId);

    @GET("places/getCurrentPlaceInfo")
    Call<sPlace> getCurrentPlace(@Query("poiId") String poiId);
}