package com.example.user.placeapp.Maps;

import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class RetrofitClient {

    static private RetrofitClient instance;
    private Retrofit retrofit;

    static public RetrofitClient getInstance(){
        synchronized (RetrofitClient.class){
            if(instance == null) {
                instance = new RetrofitClient();
            }

        }
        return instance;
    }
    public Retrofit getRetrofit(URL baseurl){
        synchronized (RetrofitClient.class){
            if(retrofit == null){
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseurl.toString())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }
        }

        return retrofit;
    }
}
