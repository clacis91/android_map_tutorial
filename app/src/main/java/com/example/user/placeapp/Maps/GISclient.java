package com.example.user.placeapp.Maps;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.placeapp.kPOJO.Geocode;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GISclient extends RetrofitClient {
    private Interceptor interceptor;
    private Retrofit retrofit;

    public void GISclient() {
    }

    /**
     * GISclient API key를 retrofit header에 default로 붙여주기 위한 method
     */
    public void authHeaderBuilder(@NonNull String api_key) {
        interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request newRequest;
                if (api_key != null && !api_key.equals("")) {
                    newRequest = chain.request().newBuilder().addHeader("Authorization", api_key).build();
                } else {
                    // api_key가 설정이 안됐으면 안된대로 그냥 req 보내서 server로부터 auth error 리턴받도록
                    newRequest = chain.request();
                }
                return chain.proceed(newRequest);
            }
        };

        this.setInterceptor(interceptor);
        retrofit = this.getRetrofit();
    }

    public void callGeocode(String param) {
        // TODO: authHeader Check
        // TODO: retrofit Check

        Call<Geocode> call = retrofit.create(GISinterface.class).getGeocode(param);
        callGISapi(call);
    }

    public <T> void callGISapi(GoogleMapContract.Model.OnFinishedListener onFinishedListener, Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    Log.d("geocode_test", String.valueOf(response.toString()));
                    //Log.d("geocode_test", String.valueOf(response.body().getResidentialAddress().size()));
                    //onFinishedListener.onGeocodeFinished(response);
                } catch (Exception e) {
                    Log.d("geocode_test", "There is an error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.d("geocode_fail", call.toString());
                Log.d("geocode_fail", t.toString());
            }
        });
    }

    private <T> Response<T> gisClientListener(Response<T> response) {
        return response;
    }

    //public GISclient getGISclient() {
    //}

}
