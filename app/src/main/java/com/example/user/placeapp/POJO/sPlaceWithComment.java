package com.example.user.placeapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class sPlaceWithComment implements Serializable {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("__v")
    @Expose
    private int __v;
    @SerializedName("poiId")
    @Expose
    private String poiId;
    @SerializedName("placePicUrl")
    @Expose
    private ArrayList<String> placePicUrl;
    @SerializedName("comments")
    @Expose
    private ArrayList<sComment> comments;

    public ArrayList<String> getPlacePicUrl() {
        return placePicUrl;
    }

    public ArrayList<sComment> getComments() {
        return comments;
    }

    public String getpoiId() {
        return poiId;
    }
}