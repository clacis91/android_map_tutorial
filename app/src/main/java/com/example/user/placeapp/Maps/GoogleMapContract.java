package com.example.user.placeapp.Maps;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

public interface GoogleMapContract {

    interface Model {

        interface OnFinishedListener {
            //public void onNearbyFinished(Response<Nearby> response);
            //public void onNearbyFailure(Throwable t);

            //public void onPlaceFinished(Place place, Bitmap placePhoto);
            //public void onPlaceFailure(Throwable t);
        }

        //public void callNearby(OnFinishedListener onFinishedListener, LatLng curPos, String type);
        //public void callPlaceInfo(OnFinishedListener onFinishedListener, PlacesClient placesClient, String placeId);
    }

    interface Presenter {

        //public void getNearby(LatLng curPos, String type);
        //public void getPlaceInfo(PlacesClient placesClient, String placeId);
    }

    interface View {

         //public void drawNearbyMarker(HashMap<LatLng,String> list);
         //public void drawPlaceInfo(Place place, Bitmap placePhoto);
    }
}
