
package com.example.user.placeapp.kPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoadAddress {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("siDo")
    @Expose
    private String siDo;
    @SerializedName("siGunGu")
    @Expose
    private String siGunGu;
    @SerializedName("siGunGu1")
    @Expose
    private String siGunGu1;
    @SerializedName("siGunGu2")
    @Expose
    private String siGunGu2;
    @SerializedName("eupMyeon")
    @Expose
    private String eupMyeon;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("streetNumber")
    @Expose
    private Integer streetNumber;
    @SerializedName("isBasement")
    @Expose
    private Boolean isBasement;
    @SerializedName("streetId")
    @Expose
    private Integer streetId;
    @SerializedName("fullAddress")
    @Expose
    private String fullAddress;
    @SerializedName("geographicInformation")
    @Expose
    private GeographicInformation geographicInformation;
    @SerializedName("rbucode")
    @Expose
    private String rbucode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSiDo() {
        return siDo;
    }

    public void setSiDo(String siDo) {
        this.siDo = siDo;
    }

    public String getSiGunGu() {
        return siGunGu;
    }

    public void setSiGunGu(String siGunGu) {
        this.siGunGu = siGunGu;
    }

    public String getSiGunGu1() {
        return siGunGu1;
    }

    public void setSiGunGu1(String siGunGu1) {
        this.siGunGu1 = siGunGu1;
    }

    public String getSiGunGu2() {
        return siGunGu2;
    }

    public void setSiGunGu2(String siGunGu2) {
        this.siGunGu2 = siGunGu2;
    }

    public String getEupMyeon() {
        return eupMyeon;
    }

    public void setEupMyeon(String eupMyeon) {
        this.eupMyeon = eupMyeon;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Boolean getIsBasement() {
        return isBasement;
    }

    public void setIsBasement(Boolean isBasement) {
        this.isBasement = isBasement;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public GeographicInformation getGeographicInformation() {
        return geographicInformation;
    }

    public void setGeographicInformation(GeographicInformation geographicInformation) {
        this.geographicInformation = geographicInformation;
    }

    public String getRbucode() {
        return rbucode;
    }

    public void setRbucode(String rbucode) {
        this.rbucode = rbucode;
    }

}
