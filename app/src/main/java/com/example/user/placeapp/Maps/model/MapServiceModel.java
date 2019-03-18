package com.example.user.placeapp.Maps.model;

import android.util.Log;

import com.example.user.placeapp.POJO.sAccess;
import com.example.user.placeapp.POJO.sPlace;

import java.util.ArrayList;

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
            }
        });
    }
}

/*
우면 한라점 poi : a8d4543d-ce5f-3bf8-821f-2cce5b090769

우면 iT 학교점 poi : b4b37697-1f97-3148-815d-f0c0e733e45f

cu 중구 세종대로점 poi : 331ca334-0ca5-30b2-90b2-1a00b1e87eda

cu 코리아나 호텔점 poi :
f58eaf93-4913-3c18-a314-e8da0cf7153e
 */

/*
[
    {
        "placePicUrl": [
            "https://gisprj.s3.ap-northeast-2.amazonaws.com/poi/pictures/b4b37697-1f97-3148-815d-f0c0e733e45f/1552540043630e2b860bd-dc70-42d4-bd8f-075493da72a7",
            "https://gisprj.s3.ap-northeast-2.amazonaws.com/poi/pictures/b4b37697-1f97-3148-815d-f0c0e733e45f/15525401281462baf366c-f950-4fe8-8063-03ef1cbe01a1"
        ],
        "comments": [],
        "_id": "5c89e18be224e0794180acaa",
        "poiId": "b4b37697-1f97-3148-815d-f0c0e733e45f",
        "__v": 1
    },
    {
        "comments": [],
        "_id": "",
        "poiId": "331ca334-0ca5-30b2-90b2-1a00b1e87eda",
        "__v": 4
    }
]
 */