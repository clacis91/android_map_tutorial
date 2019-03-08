package com.example.user.placeapp.kPOJO.Geocode;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class GeocodeResult {
    private List<ParcelAddress> parcelAddress = null;
    private List<RoadAddress> roadAddress = null;

    public List<ParcelAddress> getParcelAddress() {
        return parcelAddress;
    }
    public void setParcelAddress(List<ParcelAddress> parcelAddress) { this.parcelAddress = parcelAddress; }
    public List<RoadAddress> getRoadAddress() {
        return roadAddress;
    }
    public void setRoadAddress(List<RoadAddress> roadAddress) {
        this.roadAddress = roadAddress;
    }
}
