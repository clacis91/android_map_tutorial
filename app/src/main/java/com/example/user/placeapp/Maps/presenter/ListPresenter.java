package com.example.user.placeapp.Maps.presenter;

import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.example.user.placeapp.Maps.placeListContract;
import com.example.user.placeapp.POJO.sPlace;
import com.kt.place.sdk.net.PoiResponse;

import java.util.ArrayList;
import java.util.HashMap;

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
        //poiNames = new ArrayList<>();
        poiContent = new HashMap<>();
        placeListModel.callMyPlaceList(fbId, new MapServiceModel.callMyPlaceListListener() {
            @Override
            public void onGetMyPlaceListFinished(ArrayList<sPlace> sPlaceList) {
                for(sPlace place : sPlaceList) {
                    getPoiNameByRetrieve(place);
                }

                while(sPlaceList.size() != poiContent.size()){} // Hashmap 완성 전까지 loop

                placeListView.setMyplaceList(poiContent);
            }

            @Override
            public void onGetMyPlaceListFailure(Throwable t) {
            }
        });
    }

    private void getPoiNameByRetrieve(final sPlace place) {
        mapsModel.callpoiRetrieve(place.getpoiId(), new MapsModel.poiRetrieveListener() {
            @Override
            public void onPoiRetrieveFinished(PoiResponse response) {
                String poiName = response.getPois().get(0).getName();
                //poiNames.add(poiName);
                poiContent.put(place, poiName);
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
