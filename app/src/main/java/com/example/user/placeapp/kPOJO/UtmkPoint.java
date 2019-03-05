
package com.example.user.placeapp.kPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtmkPoint {

    @SerializedName("x")
    @Expose
    private Double x;
    @SerializedName("y")
    @Expose
    private Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

}
