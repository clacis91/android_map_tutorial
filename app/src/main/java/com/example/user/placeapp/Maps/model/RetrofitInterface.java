package com.example.user.placeapp.Maps.model;

import com.example.user.placeapp.POJO.sAccess;
import com.example.user.placeapp.POJO.sPlace;
import com.example.user.placeapp.POJO.sPlaceOverview;
import com.example.user.placeapp.POJO.sPlaceWithComment;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

    public class addReviewBody {
        private String fbId;
        private String poiId;
        private String captionTitle;
        private String captionBody;

        public addReviewBody(String fbId, String poiId, String captionTitle, String captionBody) {
            this.fbId = fbId;
            this.poiId = poiId;
            this.captionTitle = captionTitle;
            this.captionBody = captionBody;
        }
    }

    @POST("user/signInOrSignUp")
    Call<sAccess> getSignCheck(@Body signCheckRequest body);

    @Multipart
    @POST("places/addPlaceInfo")
    Call<sPlaceOverview> addPhoto(@Query("poiId") String poiId,
                                  @Part MultipartBody.Part file);

    @POST("comments/addCommentToPoi")
    Call<sPlaceWithComment> addReview(@Body addReviewBody body);

    @GET("places/getMyPlacesLists")
    Call<ArrayList<sPlace>> getMyPlacesLists(@Query("fbId") String fbId);

    @GET("places/getCurrentPlaceInfo")
    Call<sPlaceWithComment> getCurrentPlace(@Query("poiId") String poiId);
}