package com.example.user.placeapp.Maps.model;

import com.example.user.placeapp.POJO.sPlace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapServiceModel {
    //Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

    public List<sPlace> CallPlaceList() {
    // public void CallPlaceList() {
        /*
        Retrofit retrofit = retrofitBuilder.baseUrl("52.79.72.47:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        Call<sPlace> call = retrofit.create(RetrofitInterface.class).postPlaceList();

        call.enqueue(new Callback<sPlace>() {
            @Override
            public void onResponse(Call<sPlace> call, Response<sPlace> response) {

            }

            @Override
            public void onFailure(Call<sPlace> call, Throwable t) {

            }
        });
        */
        sPlace tmp1 = new sPlace();
        tmp1.setPlacePicUrl("https://gisprj.s3.ap-northeast-2.amazonaws.com/poi/pictures/b4b37697-1f97-3148-815d-f0c0e733e45f/1552540043630e2b860bd-dc70-42d4-bd8f-075493da72a7");
        tmp1.setPlacePicUrl("https://gisprj.s3.ap-northeast-2.amazonaws.com/poi/pictures/b4b37697-1f97-3148-815d-f0c0e733e45f/15525401281462baf366c-f950-4fe8-8063-03ef1cbe01a1");
        tmp1.set_id("5c89e18be224e0794180acaa");
        tmp1.set_poiId("b4b37697-1f97-3148-815d-f0c0e733e45f");
        tmp1.set__v(1);

        sPlace tmp2 = new sPlace();
        tmp2.setPlacePicUrl("https://gisprj.s3.ap-northeast-2.amazonaws.com/poi/pictures/b4b37697-1f97-3148-815d-f0c0e733e45f/15525401281462baf366c-f950-4fe8-8063-03ef1cbe01a1");
        tmp1.set_id("5c89e18be224e0794180acab");
        tmp2.set_poiId("331ca334-0ca5-30b2-90b2-1a00b1e87eda");
        tmp2.set__v(4);

        List<sPlace> rtn = new ArrayList<>();
        rtn.add(tmp1);
        rtn.add(tmp2);

        return rtn;
    }

}

// server : 52.79.72.47:3000/
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