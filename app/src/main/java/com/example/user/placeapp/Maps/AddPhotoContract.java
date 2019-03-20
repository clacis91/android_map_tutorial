package com.example.user.placeapp.Maps;

import android.net.Uri;

import com.example.user.placeapp.POJO.sPlaceReview;

import java.util.List;

public interface AddPhotoContract {

    interface Presenter {

        public void submitPhoto(String poiId, String imagePath);
    }

    interface View {

        public void submitFinished(sPlaceReview placeReview);
    }
}
