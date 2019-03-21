package com.example.user.placeapp.Maps;

import com.kt.place.sdk.model.Poi;

import java.util.List;

public interface OverviewContract {

    interface Presenter {

        public void getOverviewInfo(String poiId);
    }

    interface View {

        public void setOverviewImage(List<String> poiImage);
        public void setOverviewInfo(Poi place);
    }
}
