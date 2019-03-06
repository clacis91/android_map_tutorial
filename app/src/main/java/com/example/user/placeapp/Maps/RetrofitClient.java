package com.example.user.placeapp.Maps;

import java.net.URL;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    static private RetrofitClient instance;
    protected Retrofit.Builder retrofitBuilder;
    //private Retrofit retrofit;

    static public RetrofitClient getInstance(){
        synchronized (RetrofitClient.class){
            if(instance == null) {
                instance = new RetrofitClient();
            }

        }
        return instance;
    }

    public void setBaseurl(URL baseurl){
        synchronized (RetrofitClient.class){
            if(retrofitBuilder == null){
                retrofitBuilder = new Retrofit.Builder();
            }
            retrofitBuilder.baseUrl(baseurl.toString())
                    .addConverterFactory(GsonConverterFactory.create());
        }
    }

    public void setInterceptor(Interceptor interceptor){
        synchronized (RetrofitClient.class){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.interceptors().add(interceptor);
            OkHttpClient okHttpClientclient = builder.build();

            if(retrofitBuilder == null){
                retrofitBuilder = new Retrofit.Builder();
            }
            retrofitBuilder.client(okHttpClientclient);
        }
    }

    public Retrofit getRetrofit() {
        synchronized (RetrofitClient.class) {
            //if (retrofitBuilder != null) {
                return retrofitBuilder.build();
            //}
            // TODO: Append Exception
        }
    }
}
