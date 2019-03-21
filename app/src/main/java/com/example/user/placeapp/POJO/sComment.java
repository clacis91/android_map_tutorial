package com.example.user.placeapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sComment {
    @SerializedName("__v")
    @Expose
    private String __v;
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("poiId")
    @Expose
    private String poiId;
    @SerializedName("captionTitle")
    @Expose
    private String captionTitle;
    @SerializedName("captionBody")
    @Expose
    private String captionBody;
    @SerializedName("user")
    @Expose
    private sUser user;

    public sUser getUser() {
        return user;
    }

    public String getCaptionTitle() {
        return captionTitle;
    }

    public String getCaptionBody() {
        return captionBody;
    }
}
