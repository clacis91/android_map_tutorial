package com.example.user.placeapp.Maps.presenter;

import android.util.Log;

import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.example.user.placeapp.Maps.MyListContract;
import com.example.user.placeapp.POJO.sPlace;
import com.kt.place.sdk.model.Poi;
import com.kt.place.sdk.net.PoiResponse;

import java.util.ArrayList;
import java.util.HashMap;

public class MyListPresenter implements MyListContract.Presenter {
    MyListContract.View myListView;
    MapServiceModel mapServiceModel;
    MapsModel mapsModel;
    ArrayList<String> poiNames;
    HashMap<sPlace, String> poiContent;

    public MyListPresenter(MyListContract.View myListView) {
        this.myListView = myListView;

        mapServiceModel = new MapServiceModel();
        mapsModel = new MapsModel();
    }

    @Override
    public void getMyplaceList(String fbId) {
        mapServiceModel.callMyPlaceList(fbId, new MapServiceModel.callMyPlaceListListener() {
            @Override
            public void onGetMyPlaceListFinished(ArrayList<sPlace> sPlaceList) {
                Log.d("GetMyPlaceList", "onGetMyPlaceListFinished: ");
                myListView.setMyplaceList(sPlaceList);
                for(int i = 0; i < sPlaceList.size(); i++) {
                    getPoiNameByRetrieve(i, sPlaceList.get(i));
                }
            }

            @Override
            public void onGetMyPlaceListFailure(Throwable t) {
                Log.d("GetMyPlaceList", "onGetMyPlaceListFailure: ");
            }
        });
    }

    private void getPoiNameByRetrieve(final int position, sPlace place) {
        mapsModel.callpoiRetrieve(place.getpoiId(), new MapsModel.poiRetrieveListener() {
            @Override
            public void onPoiRetrieveFinished(PoiResponse response) {
                Poi poi = response.getPois().get(0);
                String poiName = poi.getName() + " " + poi.getBranch();
                //poiContent.put(place, poiName);
                myListView.setMyplaceList(position, poiName);
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
