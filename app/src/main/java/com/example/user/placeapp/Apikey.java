package com.example.user.placeapp;

import android.support.annotation.Nullable;

public final class Apikey {
    @Nullable
    private volatile String apikey;

    public void Apikey() {
    }

    public final synchronized void setApikey(String apikey) {
        this.apikey = apikey;
    }

    //public void setApikey(String apikey) {
    //    this.apikey = apikey;
    //}

    public String getApikey() {
        return apikey;
    }
}
