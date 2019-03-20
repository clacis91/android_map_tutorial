package com.example.user.placeapp.Maps.presenter;

import com.example.user.placeapp.Maps.MapsContract;
import com.example.user.placeapp.Maps.PoiContract;
import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.example.user.placeapp.POJO.sPlace;
import com.kt.place.sdk.model.Poi;
import com.kt.place.sdk.net.PoiResponse;

import java.util.List;

public class PoiPresenter implements PoiContract.Presenter {
    MapsModel mapsModel;
    MapServiceModel mapServiceModel;
    PoiContract.View poiView;

    public PoiPresenter(PoiContract.View poiView) {

        this.poiView = poiView;

        mapsModel = new MapsModel();
        mapServiceModel = new MapServiceModel();
    }

    @Override
    public void getPoiInfo(String poiId) {
        mapServiceModel.callCurrentPlace(poiId, new MapServiceModel.callCurrentPlaceListener() {
            @Override
            public void onGetCurrentPlaceFinished(sPlace response) {
                List<String> imageUrls = response.getPlacePicUrl();
                poiView.setPoiImage(imageUrls);
            }

            @Override
            public void onGetCurrentPlaceFailure(Throwable t) {

            }
        });

        mapsModel.callpoiRetrieve(poiId, new MapsModel.poiRetrieveListener() {
            @Override
            public void onPoiRetrieveFinished(PoiResponse response) {
                Poi poi = response.getPois().get(0);
                poiView.setPoiInfo(poi);
            }

            @Override
            public void onPoiRetrieveFailure(Throwable t) {

            }
        });
    }
}
