
package com.example.user.placeapp.kPOJO.Poi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("siDo")
    @Expose
    private String siDo;
    @SerializedName("siGunGu")
    @Expose
    private String siGunGu;
    @SerializedName("eupMyeonDong")
    @Expose
    private String eupMyeonDong;
    @SerializedName("haengJeongDong")
    @Expose
    private String haengJeongDong;
    @SerializedName("ri")
    @Expose
    private String ri;
    @SerializedName("houseNumber")
    @Expose
    private String houseNumber;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("streetNumber")
    @Expose
    private String streetNumber;
    @SerializedName("eupMyeon")
    @Expose
    private String eupMyeon;

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

    public String getEupMyeonDong() {
        return eupMyeonDong;
    }

    public void setEupMyeonDong(String eupMyeonDong) {
        this.eupMyeonDong = eupMyeonDong;
    }

    public String getHaengJeongDong() {
        return haengJeongDong;
    }

    public void setHaengJeongDong(String haengJeongDong) {
        this.haengJeongDong = haengJeongDong;
    }

    public String getRi() {
        return ri;
    }

    public void setRi(String ri) {
        this.ri = ri;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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

    public String getEupMyeon() {
        return eupMyeon;
    }

    public void setEupMyeon(String eupMyeon) {
        this.eupMyeon = eupMyeon;
    }

}
