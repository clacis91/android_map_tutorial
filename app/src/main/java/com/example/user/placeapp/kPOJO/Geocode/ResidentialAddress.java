
package com.example.user.placeapp.kPOJO.Geocode;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResidentialAddress {

    @SerializedName("parcelAddress")
    @Expose
    private List<ParcelAddress> parcelAddress = null;
    @SerializedName("roadAddress")
    @Expose
    private List<RoadAddress> roadAddress = null;

    public List<ParcelAddress> getParcelAddress() {
        return parcelAddress;
    }

    public void setParcelAddress(List<ParcelAddress> parcelAddress) {
        this.parcelAddress = parcelAddress;
    }

    public List<RoadAddress> getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(List<RoadAddress> roadAddress) {
        this.roadAddress = roadAddress;
    }

}
