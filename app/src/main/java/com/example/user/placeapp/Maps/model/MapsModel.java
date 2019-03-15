package com.example.user.placeapp.Maps.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.placeapp.Maps.GoogleMapContract;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.kt.place.sdk.util.Client;

import java.util.Arrays;
import java.util.List;

public class MapsModel implements GoogleMapContract.Model {

    //@Override
    public void callNearby(OnFinishedListener onFinishedListener, LatLng curPos, String type) {
        // Nearby Request 생성
        // Call 수행
    }

    //@Override
    //public void callPlaceInfo(OnFinishedListener onFinishedListener, Client placeClient, String placeId) {
        // Request 생성
        // Call 수행
    //}

}
