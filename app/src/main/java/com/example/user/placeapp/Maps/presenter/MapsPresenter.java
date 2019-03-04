package com.example.user.placeapp.Maps.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.user.placeapp.Maps.GoogleMapContract;
import com.example.user.placeapp.Maps.model.MapsModel;
import com.example.user.placeapp.POJO.Nearby;
import com.example.user.placeapp.POJO.NearbyResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsPresenter implements GoogleMapContract.Presenter, GoogleMapContract.Model.OnFinishedListener {

    GoogleMapContract.View view;
    GoogleMapContract.Model model;

    public MapsPresenter(GoogleMapContract.View view) {

        this.view = view;
        model = new MapsModel();
    }

    @Override
    public void getNearby(LatLng curPos, String type, String googleApiKey) {
        model.callNearby(this, curPos, type, googleApiKey);
    }

    @Override
    public void onNearbyFinished(Response<Nearby> response) {

        HashMap<LatLng,String> responseMap = getDataForMarker(response);
        view.drawMarker(responseMap);
    }

    @Override
    public void onNearbyFailure(Throwable t) {
    }

    private HashMap<LatLng,String> getDataForMarker(Response<Nearby> response) {
        HashMap<LatLng,String> responseMap = new HashMap<>();

        for (int i = 0; i < response.body().getResults().size(); i++) {
            NearbyResult result = response.body().getResults().get(i);

            Double lat = result.getGeometry().getLocation().getLat();
            Double lng = result.getGeometry().getLocation().getLng();
            LatLng latlng = new LatLng(lat, lng);
            String markerPlaceId = result.getPlaceId();

            responseMap.put(latlng, markerPlaceId);
        }

        return responseMap;
    }


    @Override
    public void getPlaceInfo(PlacesClient placesClient, String placeId) {
        model.callPlaceInfo(this, placesClient, placeId);
    }

    @Override
    public void onPlaceFinished(Place place, Bitmap placePhoto) {
        view.drawPlaceInfo(place, placePhoto);
    }

    @Override
    public void onPlaceFailure(Throwable t) {
    }
}
