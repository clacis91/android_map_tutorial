
package com.example.user.placeapp.kPOJO.Poi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extension {

    @SerializedName("extensionSize")
    @Expose
    private Integer extensionSize;
    @SerializedName("homepageURL")
    @Expose
    private String homepageURL;
    @SerializedName("parkingService")
    @Expose
    private Boolean parkingService;
    @SerializedName("creditcardService")
    @Expose
    private Boolean creditcardService;
    @SerializedName("selfService")
    @Expose
    private Boolean selfService;
    @SerializedName("disabledService")
    @Expose
    private Boolean disabledService;
    @SerializedName("petService")
    @Expose
    private Boolean petService;

    public Integer getExtensionSize() {
        return extensionSize;
    }

    public void setExtensionSize(Integer extensionSize) {
        this.extensionSize = extensionSize;
    }

    public String getHomepageURL() {
        return homepageURL;
    }

    public void setHomepageURL(String homepageURL) {
        this.homepageURL = homepageURL;
    }

    public Boolean getParkingService() {
        return parkingService;
    }

    public void setParkingService(Boolean parkingService) {
        this.parkingService = parkingService;
    }

    public Boolean getCreditcardService() {
        return creditcardService;
    }

    public void setCreditcardService(Boolean creditcardService) {
        this.creditcardService = creditcardService;
    }

    public Boolean getSelfService() {
        return selfService;
    }

    public void setSelfService(Boolean selfService) {
        this.selfService = selfService;
    }

    public Boolean getDisabledService() {
        return disabledService;
    }

    public void setDisabledService(Boolean disabledService) {
        this.disabledService = disabledService;
    }

    public Boolean getPetService() {
        return petService;
    }

    public void setPetService(Boolean petService) {
        this.petService = petService;
    }

}
