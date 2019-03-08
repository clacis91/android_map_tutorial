package com.example.user.placeapp.Maps;

import com.example.user.placeapp.kPOJO.Geocode.GeocodeResponse;
import com.example.user.placeapp.kPOJO.Geocode.GeocodeResult;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public interface OnGisResponse {

    //interface OnGeocodeResponse {
        public void OnSuccess(List<GeocodeResult> response);
        public void OnFailure();
    //}
}
