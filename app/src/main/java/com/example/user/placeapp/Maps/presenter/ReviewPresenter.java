package com.example.user.placeapp.Maps.presenter;

import com.example.user.placeapp.Maps.ReviewContract;
import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.example.user.placeapp.POJO.sComment;
import com.example.user.placeapp.POJO.sPlaceWithComment;

import java.util.ArrayList;

public class ReviewPresenter implements ReviewContract.Presenter {
    MapsModel mapsModel;
    MapServiceModel mapServiceModel;
    ReviewContract.View reviewView;

    public ReviewPresenter(ReviewContract.View reviewView) {

        this.reviewView = reviewView;

        mapsModel = new MapsModel();
        mapServiceModel = new MapServiceModel();
    }

    @Override
    public void getReviewList(String poiId) {
        mapServiceModel.callCurrentPlace(poiId, new MapServiceModel.callCurrentPlaceListener() {
            @Override
            public void onGetCurrentPlaceFinished(sPlaceWithComment response) {
                ArrayList<sComment> comments = (ArrayList<sComment>) response.getComments();
                reviewView.setReviewList(comments);
            }

            @Override
            public void onGetCurrentPlaceFailure(Throwable t) {

            }
        });
    }
}
