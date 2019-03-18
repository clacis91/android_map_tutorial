package com.example.user.placeapp.Maps.presenter;

import com.example.user.placeapp.Maps.MapsContract;
import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.example.user.placeapp.POJO.sAccess;
import com.google.android.gms.maps.model.LatLng;


public class MapsPresenter implements MapsContract.Presenter {

    MapsContract.View mapsView;
    MapsModel mapsModel;
    MapServiceModel mapServiceModel;

    public MapsPresenter(MapsContract.View mapsView) {

        this.mapsView = mapsView;

        mapsModel = new MapsModel();
        mapServiceModel = new MapServiceModel();
    }

    @Override
    public void getSignCheck(String accessToken) {
        mapServiceModel.callSignCheck(accessToken, new MapServiceModel.callSignCheckListener() {
            @Override
            public void onSignCheckFinished(sAccess response) {
                mapsView.setFbInfo(response);
            }

            @Override
            public void onSignCheckFailure(Throwable t) {
            }
        });
    }
}
