package com.example.user.placeapp.Maps.presenter;

import android.util.Log;

import com.example.user.placeapp.Maps.AddPhotoContract;
import com.example.user.placeapp.Maps.AddReviewContract;
import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.POJO.sPlaceOverview;
import com.example.user.placeapp.POJO.sPlaceWithComment;

import java.io.File;

public class AddReviewPresenter implements AddReviewContract.Presenter {
    AddReviewContract.View addReviewView;
    MapServiceModel mapServiceModel;

    public AddReviewPresenter(AddReviewContract.View addReviewView) {
        this.addReviewView = addReviewView;

        mapServiceModel = new MapServiceModel();
    }

    @Override
    public void submitReview(String fbId, String poiId, String commentTitle, String commentBody) {
        mapServiceModel.addPlaceReview(fbId, poiId, commentTitle, commentBody, new MapServiceModel.addPlaceReviewListener() {

            @Override
            public void onAddPlaceReviewFinished(sPlaceWithComment response) {
                addReviewView.submitFinished(response);
            }

            @Override
            public void onAddPlaceReviewFailure(Throwable t) {

            }
        });
    }
}
