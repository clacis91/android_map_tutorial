
package com.example.user.placeapp.kPOJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeocodeResult {

    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("residentialAddress")
    @Expose
    private List<ResidentialAddress> residentialAddress = null;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<ResidentialAddress> getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(List<ResidentialAddress> residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

}
