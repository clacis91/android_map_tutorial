package com.example.user.placeapp.Maps;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.placeapp.Apikey;

import java.net.MalformedURLException;
import java.net.URL;

public class GISmanager {
    private static final Apikey mApiKey = new Apikey();
    //private static GISinterface gisInterface;
    //code convention : Lint

    public GISmanager() {
    }

    public static synchronized void initialize(@NonNull String apikey) {
        // API key init
        // Context 상에서
        mApiKey.setApikey(apikey);
    }

    public static synchronized boolean isInitialized() {
        return true;
    }

    //@NonNull
    //public static synchronized GISclient beforeCreateClient() {
    //}

    //public static synchronized GISclient createClient() {
    public static GISclient createClient() {
        //retrofit client 생성
        //okhttp interceptor로 client 생성 시 API key header 처리
        GISclient gisClient = new GISclient();

        URL baseurl = null;
        try {
            // 도메인 아래 sub url은 따로 관리해야한다
            baseurl = new URL("https://gis.kt.com/search/v1.0/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        gisClient.setBaseurl(baseurl);
        gisClient.authHeaderBuilder(mApiKey.getApikey());

        return gisClient;
    }
}
