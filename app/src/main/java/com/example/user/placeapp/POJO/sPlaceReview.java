package com.example.user.placeapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class sPlaceReview implements Serializable {
    @SerializedName("placePicUrl")
    @Expose
    private List<String> placePicUrl = new ArrayList<>();
    @SerializedName("comments")
    @Expose
    private List<String> comments = new ArrayList<>();

    public List<String> getPlacePicUrl() {
        return placePicUrl;
    }

    public List<String> comments() {
        return comments;
    }
}
