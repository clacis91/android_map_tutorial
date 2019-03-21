package com.example.user.placeapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class sUser {
    @SerializedName("__v")
    @Expose
    private String __v;
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("fbId")
    @Expose
    private String fbId;
    @SerializedName("userProfileUrl")
    @Expose
    private String userProfileUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("places")
    @Expose
    private ArrayList<String> places;

    public String getUserProfileUrl() {
        return this.userProfileUrl;
    }

    public String getName() {
        return this.name;
    }
}
