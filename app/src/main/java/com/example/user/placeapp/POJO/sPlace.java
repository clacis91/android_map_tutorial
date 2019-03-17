package com.example.user.placeapp.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class sPlace {
    @SerializedName("placePicUrl")
    @Expose
    private List<String> placePicUrl = new ArrayList<>();
    @SerializedName("comments")
    @Expose
    private List<String> comments = new ArrayList<>();
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("poiId")
    @Expose
    private String poiId;
    @SerializedName("__v")
    @Expose
    private int __v;

    public List<String> getPlacePicUrl() {
        return placePicUrl;
    }
    public List<String> getComments() {
        return comments;
    }
    public String get_id() {
        return _id;
    }
    public String getpoiId() {
        return poiId;
    }
    public int get__v() {
        return __v;
    }

    public void setPlacePicUrl(String placePicUrl) {
        this.placePicUrl.add(placePicUrl);
    }
    public void setComments(String comments) {
        this.comments.add(comments);
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public void set_poiId(String poiId) {
        this.poiId = poiId;
    }
    public void set__v(int __v) {
        this.__v = __v;
    }
}

/*
"[
    {
        ""placePicUrl"": [
            ""https://gisprj.s3.ap-northeast-2.amazonaws.com/poi/pictures/b4b37697-1f97-3148-815d-f0c0e733e45f/1552540043630e2b860bd-dc70-42d4-bd8f-075493da72a7"",
            ""https://gisprj.s3.ap-northeast-2.amazonaws.com/poi/pictures/b4b37697-1f97-3148-815d-f0c0e733e45f/15525401281462baf366c-f950-4fe8-8063-03ef1cbe01a1""
        ],
        ""comments"": [],
        ""_id"": ""5c89e18be224e0794180acaa"",
        ""poiId"": ""b4b37697-1f97-3148-815d-f0c0e733e45f"",
        ""__v"": 1
    }
]"
 */