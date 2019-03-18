package com.example.user.placeapp.Maps.presenter;

import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.example.user.placeapp.Maps.placeListContract;
import com.example.user.placeapp.POJO.sPlace;
import com.kt.place.sdk.net.PoiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.transform.Result;

public class ListPresenter implements placeListContract.Presenter {
    placeListContract.View placeListView;
    MapServiceModel placeListModel;
    MapsModel mapsModel;
    ArrayList<String> poiNames;
    HashMap<sPlace, String> poiContent;

    public ListPresenter(placeListContract.View placeListView) {
        this.placeListView = placeListView;

        placeListModel = new MapServiceModel();
        mapsModel = new MapsModel();
    }

    @Override
    public void getMyplaceList(String fbId) {
        placeListModel.callMyPlaceList(fbId, new MapServiceModel.callMyPlaceListListener() {
            @Override
            public void onGetMyPlaceListFinished(ArrayList<sPlace> sPlaceList) {
                placeListView.setMyplaceList(sPlaceList);
                for(int i = 0; i < sPlaceList.size(); i++) {
                    getPoiNameByRetrieve(i, sPlaceList.get(i));
                }
            }

            @Override
            public void onGetMyPlaceListFailure(Throwable t) {
            }
        });
    }

    private void getPoiNameByRetrieve(final int position, sPlace place) {
        mapsModel.callpoiRetrieve(place.getpoiId(), new MapsModel.poiRetrieveListener() {
            @Override
            public void onPoiRetrieveFinished(PoiResponse response) {
                String poiName = response.getPois().get(0).getName();
                //poiContent.put(place, poiName);
                placeListView.setMyplaceList(position, poiName);
            }

            @Override
            public void onPoiRetrieveFailure(Throwable t) {

            }
        });
    }

    @Override
    public void getMyplaceListPoi(String poiId) {
        mapsModel.callpoiRetrieve(poiId, new MapsModel.poiRetrieveListener() {
            @Override
            public void onPoiRetrieveFinished(PoiResponse response) {
                //placeListView.setMyplaceListPoi(response);
            }

            @Override
            public void onPoiRetrieveFailure(Throwable t) {

            }
        });
    }
}
