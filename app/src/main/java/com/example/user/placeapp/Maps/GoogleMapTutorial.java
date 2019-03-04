package com.example.user.placeapp.Maps;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface GoogleMapTutorial {

     interface GoogleMapPresenter{

        public void getNearby(LatLng curPos, String type, String googleApiKey);
    }

    interface GoogleMapView{

         public void drawMarker(List<LatLng> list);
    }
}
