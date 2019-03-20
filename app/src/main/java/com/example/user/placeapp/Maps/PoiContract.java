package com.example.user.placeapp.Maps;

import com.kt.place.sdk.model.Poi;

import java.util.List;

public interface PoiContract {

    interface Presenter {

        public void getPoiInfo(String poiId);
    }

    interface View {

        public void setPoiImage(List<String> poiImage);
        public void setPoiInfo(Poi place);
    }
}
