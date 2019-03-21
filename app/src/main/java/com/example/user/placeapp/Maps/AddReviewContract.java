package com.example.user.placeapp.Maps;

import com.example.user.placeapp.POJO.sPlaceWithComment;

public interface AddReviewContract {

    interface Presenter {

        public void submitReview(String fbId, String poiId, String commentTitle, String commentBody);
    }

    interface View {

        public void submitFinished(sPlaceWithComment placeReview);
    }
}
