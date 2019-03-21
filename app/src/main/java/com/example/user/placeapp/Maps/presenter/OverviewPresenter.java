package com.example.user.placeapp.Maps.presenter;

import com.example.user.placeapp.Maps.OverviewContract;
import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.example.user.placeapp.POJO.sPlace;
import com.example.user.placeapp.POJO.sPlaceWithComment;
import com.kt.place.sdk.model.Poi;
import com.kt.place.sdk.net.PoiResponse;

import java.util.List;

public class OverviewPresenter implements OverviewContract.Presenter {
    MapsModel mapsModel;
    MapServiceModel mapServiceModel;
    OverviewContract.View overviewView;

    public OverviewPresenter(OverviewContract.View overviewView) {

        this.overviewView = overviewView;

        mapsModel = new MapsModel();
        mapServiceModel = new MapServiceModel();
    }

    @Override
    public void getOverviewInfo(String poiId) {
        mapServiceModel.callCurrentPlace(poiId, new MapServiceModel.callCurrentPlaceListener() {
            @Override
            public void onGetCurrentPlaceFinished(sPlaceWithComment response) {
                List<String> imageUrls = response.getPlacePicUrl();
                overviewView.setOverviewImage(imageUrls);
            }

            @Override
            public void onGetCurrentPlaceFailure(Throwable t) {

            }
        });

        mapsModel.callpoiRetrieve(poiId, new MapsModel.poiRetrieveListener() {
            @Override
            public void onPoiRetrieveFinished(PoiResponse response) {
                Poi poi = response.getPois().get(0);
                overviewView.setOverviewInfo(poi);
            }

            @Override
            public void onPoiRetrieveFailure(Throwable t) {

            }
        });
    }
}
