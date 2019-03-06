
package com.example.user.placeapp.kPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoadAddress {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("streetNumber")
    @Expose
    private String streetNumber;
    @SerializedName("streetId")
    @Expose
    private String streetId;
    @SerializedName("geographicInformation")
    @Expose
    private GeographicInformation geographicInformation;
    @SerializedName("siDo")
    @Expose
    private String siDo;
    @SerializedName("siGunGu1")
    @Expose
    private String siGunGu1;
    @SerializedName("isBasement")
    @Expose
    private Boolean isBasement;
    @SerializedName("fullAddress")
    @Expose
    private String fullAddress;
    @SerializedName("rbucode")
    @Expose
    private String rbucode;
    @SerializedName("siGunGu")
    @Expose
    private String siGunGu;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetId() {
        return streetId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public GeographicInformation getGeographicInformation() {
        return geographicInformation;
    }

    public void setGeographicInformation(GeographicInformation geographicInformation) {
        this.geographicInformation = geographicInformation;
    }

    public String getSiDo() {
        return siDo;
    }

    public void setSiDo(String siDo) {
        this.siDo = siDo;
    }

    public String getSiGunGu1() {
        return siGunGu1;
    }

    public void setSiGunGu1(String siGunGu1) {
        this.siGunGu1 = siGunGu1;
    }

    public Boolean getIsBasement() {
        return isBasement;
    }

    public void setIsBasement(Boolean isBasement) {
        this.isBasement = isBasement;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getRbucode() {
        return rbucode;
    }

    public void setRbucode(String rbucode) {
        this.rbucode = rbucode;
    }

    public String getSiGunGu() {
        return siGunGu;
    }

    public void setSiGunGu(String siGunGu) {
        this.siGunGu = siGunGu;
    }

}
