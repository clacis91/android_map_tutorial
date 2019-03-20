package com.example.user.placeapp.Maps.model;

import android.util.Log;

import com.example.user.placeapp.POJO.sAccess;
import com.example.user.placeapp.POJO.sPlace;
import com.example.user.placeapp.POJO.sPlaceReview;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapServiceModel  {
    Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
    Retrofit retrofit = retrofitBuilder.baseUrl("http://52.79.72.47:3000/").addConverterFactory(GsonConverterFactory.create()).build();

    public interface callMyPlaceListListener {
        public void onGetMyPlaceListFinished(ArrayList<sPlace> response);
        public void onGetMyPlaceListFailure(Throwable t);
    }

    public void callMyPlaceList(String fbId, final callMyPlaceListListener onFinishedListener) {
        Call<ArrayList<sPlace>> call = retrofit.create(RetrofitInterface.class).getMyPlacesLists(fbId);

        call.enqueue(new Callback<ArrayList<sPlace>>() {
            @Override
            public void onResponse(Call<ArrayList<sPlace>> call, Response<ArrayList<sPlace>> response) {
                onFinishedListener.onGetMyPlaceListFinished(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<sPlace>> call, Throwable t) {
                //TODO: onGetMyPlaceListFailure
            }
        });
    }

    public interface callSignCheckListener {
        public void onSignCheckFinished(sAccess response);
        public void onSignCheckFailure(Throwable t);
    }

    public void callSignCheck(String accessToken, final callSignCheckListener onFinishedListener) {
        Call<sAccess> call = retrofit.create(RetrofitInterface.class).getSignCheck(new RetrofitInterface.signCheckRequest(accessToken));

        call.enqueue(new Callback<sAccess>() {
            @Override
            public void onResponse(Call<sAccess> call, Response<sAccess> response) {
                try {
                    onFinishedListener.onSignCheckFinished(response.body());
                } catch (Exception e) {
                    Log.d("callSignCheck", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<sAccess> call, Throwable t) {
                Log.d("callSignCheck", "fail");
                //TODO: onSignCheckFailure
            }
        });
    }

    public interface callCurrentPlaceListener {
        public void onGetCurrentPlaceFinished(sPlace response);
        public void onGetCurrentPlaceFailure(Throwable t);
    }

    public void callCurrentPlace(String poiId, final callCurrentPlaceListener onFinishedListener) {
        Call<sPlace> call = retrofit.create(RetrofitInterface.class).getCurrentPlace(poiId);

        call.enqueue(new Callback<sPlace>() {
            @Override
            public void onResponse(Call<sPlace> call, Response<sPlace> response) {
                onFinishedListener.onGetCurrentPlaceFinished(response.body());
            }

            @Override
            public void onFailure(Call<sPlace> call, Throwable t) {
                //TODO: onGetCurrentPlaceFailure
            }
        });
    }

    public interface addPlacePhotoListener {
        public void onAddPlacePhotoFinished(sPlaceReview response);
        public void onAddPlacePhotoFailure(Throwable t);
    }

    public void addPlacePhoto(String poiId, File imgFile, final addPlacePhotoListener onFinishedListener) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imgFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imgFile", imgFile.getName(), requestFile);

        Call<sPlaceReview> call = retrofit.create(RetrofitInterface.class).addPhoto(poiId, body);

        Log.d("addPlacePhoto", call.request().toString());

        call.enqueue(new Callback<sPlaceReview>() {
            @Override
            public void onResponse(Call<sPlaceReview> call, Response<sPlaceReview> response) {
                onFinishedListener.onAddPlacePhotoFinished(response.body());
                Log.d("addPlacePhoto", "success");
            }

            @Override
            public void onFailure(Call<sPlaceReview> call, Throwable t) {
                //TODO: onAddPlacePhotoFailure
                Log.d("addPlacePhoto", "fail");
                Log.d("addPlacePhoto", t.getMessage().toString());
            }
        });
    }
}