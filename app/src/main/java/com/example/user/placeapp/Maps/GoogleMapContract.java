package com.example.user.placeapp.Maps;

import android.graphics.Bitmap;

import com.example.user.placeapp.POJO.Nearby;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.HashMap;

import retrofit2.Response;

public interface GoogleMapContract {

    interface Model {

        interface OnFinishedListener {
            public void onNearbyFinished(Response<Nearby> response);
            public void onNearbyFailure(Throwable t);

            public void onPlaceFinished(Place place, Bitmap placePhoto);
            public void onPlaceFailure(Throwable t);
        }

        public void callNearby(OnFinishedListener onFinishedListener, LatLng curPos, String type, String googleApiKey);
        public void callPlaceInfo(OnFinishedListener onFinishedListener, PlacesClient placesClient, String placeId);
        //public void callPhoto(PlacesClient placesClient, Place place);
    }

    interface Presenter {

        public void getNearby(LatLng curPos, String type, String googleApiKey);
        public void getPlaceInfo(PlacesClient placesClient, String placeId);
        //public void getPlacePhoto(PlacesClient placesClient);
    }

    interface View {

         public void drawMarker(HashMap<LatLng,String> list);
         public void drawPlaceInfo(Place place, Bitmap placePhoto);
    }
}
