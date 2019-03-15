package com.example.user.placeapp.Maps.presenter;

import android.graphics.Bitmap;

import com.example.user.placeapp.Maps.GoogleMapContract;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.google.android.gms.maps.model.LatLng;
//import com.kt.place.sdk.util.Client;

import java.util.HashMap;

public class MapsPresenter implements GoogleMapContract.Presenter, GoogleMapContract.Model.OnFinishedListener {

    GoogleMapContract.View view;
    GoogleMapContract.Model model;

    public MapsPresenter(GoogleMapContract.View view) {

        this.view = view;
        model = new MapsModel();
    }

    //@Override
    public void getNearby(LatLng curPos, String type, String googleApiKey) {
        //model.callNearby(this, curPos, type, googleApiKey);
    }

    //@Override
    /*public void onNearbyFinished(Response<Nearby> response) {

        HashMap<LatLng,String> responseMap = getDataForMarker(response);
        view.drawNearbyMarker(responseMap);
    }*/

    //@Override
    public void onNearbyFailure(Throwable t) {
    }

    //private HashMap<LatLng,String> getDataForMarker(Response<Nearby> response) {
    //}


    //@Override
    //public void getPlaceInfo(Client placeClient, String placeId) {
        //model.callPlaceInfo(this, placeClient, placeId);
    //}

    //@Override
    //public void onPlaceFinished(Client placeClient, Bitmap placePhoto) {
        //view.drawPlaceInfo(place, placePhoto);
    //}

    //@Override
    public void onPlaceFailure(Throwable t) {
    }
}
