
package com.example.user.placeapp.kPOJO.Poi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phones {

    @SerializedName("representation")
    @Expose
    private List<String> representation = null;

    public List<String> getRepresentation() {
        return representation;
    }

    public void setRepresentation(List<String> representation) {
        this.representation = representation;
    }

}
