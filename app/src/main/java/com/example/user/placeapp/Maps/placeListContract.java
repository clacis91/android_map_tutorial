package com.example.user.placeapp.Maps;

import com.example.user.placeapp.POJO.sPlace;
import com.kt.place.sdk.net.PoiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface placeListContract {

    interface Presenter {

        public void getMyplaceList(String fbId);
        public void getMyplaceListPoi(String poiId);
    }

    interface View {

        //public void setMyplaceList(ArrayList<sPlace> response, String poiName);
        public void setMyplaceList(HashMap<sPlace, String> poiListContent);
        //public void setMyplaceListPoi(PoiResponse response);
    }
}
