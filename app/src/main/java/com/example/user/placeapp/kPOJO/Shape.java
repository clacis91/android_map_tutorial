
package com.example.user.placeapp.kPOJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shape {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("coordinates")
    @Expose
    private List<List<List<Integer>>> coordinates = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<List<Integer>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Integer>>> coordinates) {
        this.coordinates = coordinates;
    }

}
