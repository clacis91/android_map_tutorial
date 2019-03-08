package com.example.user.placeapp.Maps;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.placeapp.kPOJO.Geocode.GeocodeResponse;
import com.example.user.placeapp.kPOJO.Geocode.GeocodeResult;
import com.example.user.placeapp.kPOJO.Geocode.ResidentialAddress;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GISclient {
    private Interceptor interceptor;
    private RetrofitClient retrofitClient;
    private Retrofit retrofit;

    public GISclient() {
        retrofitClient = RetrofitClient.getInstance();
    }

    public void setBaseurl(URL baseurl) {
        retrofitClient.setBaseurl(baseurl);
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

        retrofitClient.setInterceptor(interceptor);
        retrofit = retrofitClient.getRetrofit();
    }

    public void callGeocode(GISinterface.geocodeBody body, OnGisResponse onGisResponse) {
        // TODO: authHeader Check
        // TODO: retrofit Check
        Call<GeocodeResponse> call = retrofit.create(GISinterface.class).postGeocode(body);
        call.enqueue(OnGeocodeResponse(onGisResponse));

        /*
        call.enqueue(new Callback<GeocodeResponse>() {
            @Override
            public void onResponse(Call<GeocodeResponse> call, Response<GeocodeResponse> response) {
                try {
                    List<GeocodeResult> result = refineGeocodeResponse(response.body());
                    onGisResponse.OnSuccess(result);
                } catch (Exception e) {
                    Log.d("geocode_test", "There is an error");
                    onGisResponse.OnFailure();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<GeocodeResponse> call, Throwable t) {
                Log.d("geocode_fail", t.toString());
            }
        });
        */
    }

    public void callGeocode(String param, OnGisResponse onGisResponse) {
        Call<GeocodeResponse> call = retrofit.create(GISinterface.class).getGeocode(param, null, null);
        call.enqueue(OnGeocodeResponse(onGisResponse));
    }

    public void callGeocode(Double lat, Double lng, OnGisResponse onGisResponse) {
        Call<GeocodeResponse> call = retrofit.create(GISinterface.class).getGeocode(null, lat, lng);
        call.enqueue(OnGeocodeResponse(onGisResponse));
    }

    private List<GeocodeResult> refineGeocodeResponse(GeocodeResponse geocodeResponse) {
        List<GeocodeResult> geocodeResult = null;
        for(ResidentialAddress responseList : geocodeResponse.getResidentialAddress()) {
            GeocodeResult result = new GeocodeResult();
            result.setParcelAddress(responseList.getParcelAddress());
            result.setRoadAddress(responseList.getRoadAddress());
            geocodeResult.add(result);
        }
        return geocodeResult;
    }

    Callback<GeocodeResponse> OnGeocodeResponse(OnGisResponse onGisResponse) {

        Callback<GeocodeResponse> callback = new Callback<GeocodeResponse>() {
            @Override
            public void onResponse(Call<GeocodeResponse> call, Response<GeocodeResponse> response) {
                try {
                    List<GeocodeResult> result = refineGeocodeResponse(response.body());
                    onGisResponse.OnSuccess(result);
                } catch (Exception e) {
                    Log.d("geocode_test", "There is an error");
                    onGisResponse.OnFailure();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GeocodeResponse> call, Throwable t) {
                Log.d("geocode_fail", t.toString());
            }

        };
        return callback;
    }

    /*private <T> void callGISapi(Call<T> call, final OnGisResponse onGisCallListener) {
        Log.d("geocode_call", String.valueOf(call.request().toString()));

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {

                    onGisCallListener.OnSuccess(response);
                } catch (Exception e) {
                    Log.d("geocode_test", "There is an error");
                    onGisCallListener.OnFailure(response);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.d("geocode_fail", t.toString());
            }
        });
    }*/
}