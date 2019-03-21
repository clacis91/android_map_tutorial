package com.example.user.placeapp.Maps;

import com.example.user.placeapp.POJO.sPlaceOverview;

public interface AddPhotoContract {

    interface Presenter {

        public void submitPhoto(String poiId, String imagePath);
    }

    interface View {

        public void submitFinished(sPlaceOverview placeReview);
    }
}
