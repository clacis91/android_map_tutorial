
package com.example.user.placeapp.kPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParcelAddress {

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
    @SerializedName("eupMyeonDong")
    @Expose
    private String eupMyeonDong;
    @SerializedName("HaengJeongDong")
    @Expose
    private String haengJeongDong;
    @SerializedName("ri")
    @Expose
    private String ri;
    @SerializedName("houseNumber")
    @Expose
    private Integer houseNumber;
    @SerializedName("ismountain")
    @Expose
    private Boolean ismountain;
    @SerializedName("badmId")
    @Expose
    private Integer badmId;
    @SerializedName("isFromBName")
    @Expose
    private Boolean isFromBName;
    @SerializedName("fullAddress")
    @Expose
    private String fullAddress;
    @SerializedName("geographicInformation")
    @Expose
    private GeographicInformation geographicInformation;
    @SerializedName("pnucode")
    @Expose
    private String pnucode;

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

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Boolean getIsmountain() {
        return ismountain;
    }

    public void setIsmountain(Boolean ismountain) {
        this.ismountain = ismountain;
    }

    public Integer getBadmId() {
        return badmId;
    }

    public void setBadmId(Integer badmId) {
        this.badmId = badmId;
    }

    public Boolean getIsFromBName() {
        return isFromBName;
    }

    public void setIsFromBName(Boolean isFromBName) {
        this.isFromBName = isFromBName;
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

    public String getPnucode() {
        return pnucode;
    }

    public void setPnucode(String pnucode) {
        this.pnucode = pnucode;
    }

}
