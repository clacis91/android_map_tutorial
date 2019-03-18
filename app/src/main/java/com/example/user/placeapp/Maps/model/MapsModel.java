package com.example.user.placeapp.Maps.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.placeapp.Maps.MapsContract;
import com.example.user.placeapp.POJO.sAccess;
import com.kt.place.sdk.listener.OnSuccessListener;
import com.kt.place.sdk.net.PoiResponse;
import com.kt.place.sdk.net.RetrievePoiRequest;
import com.kt.place.sdk.util.Client;

public class MapsModel {
    Client placeClient = new Client();

    public interface poiRetrieveListener {
        public void onPoiRetrieveFinished(PoiResponse response);
        public void onPoiRetrieveFailure(Throwable t);
    }

    public void callpoiRetrieve(String poiId, final poiRetrieveListener onFinishedListener) {
        RetrievePoiRequest retrievePoiRequest = new RetrievePoiRequest.RetrievePoiRequestBuilder().setId(poiId).build();
        placeClient.getRetrievePoi(retrievePoiRequest, new OnSuccessListener<PoiResponse>() {
            @Override
            public void onSuccess(@NonNull PoiResponse poiResponse) {
                try {
                    onFinishedListener.onPoiRetrieveFinished(poiResponse);
                } catch (Exception e) {
                    Log.d("callpoiRetrieve", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Log.d("callpoiRetrieve", "fail");
            }
        });
    }
}