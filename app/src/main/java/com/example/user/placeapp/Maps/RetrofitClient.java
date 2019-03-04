package com.example.user.placeapp.Maps;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    public Retrofit getRetrofit(){
        synchronized (RetrofitClient.class){
            if(retrofit == null){
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://maps.googleapis.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }
        }

        return retrofit;
    }
}
