package com.example.user.placeapp.Maps;

import com.example.user.placeapp.POJO.Nearby;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

public interface GoogleMapContract {

    interface Model {

        interface OnFinishedListener {
            public void onNearbyFinished(Response<Nearby> response);
            public void onNearbyFailure(Throwable t);
        }

        public void callNearby(OnFinishedListener onFinishedListener, LatLng curPos, String type, String googleApiKey);
    }

    interface Presenter {

        public void getNearby(LatLng curPos, String type, String googleApiKey);
    }

    interface View {

         public void drawMarker(HashMap<LatLng,String> list);
    }
}
