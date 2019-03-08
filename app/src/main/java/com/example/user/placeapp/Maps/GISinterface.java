package com.example.user.placeapp.Maps;

import com.example.user.placeapp.kPOJO.Geocode.GeocodeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GISinterface {
    //https://gis.kt.com/search/v1.0/pois
    //https://gis.kt.com/search/v1.0/utilities/geocode

    public class Point {
        private Double lat;
        private Double lng;

        public Point(Double lat, Double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }

    public class geocodeBody {
        private Point point;

        public geocodeBody(Double lat, Double lng) {
            this.point = new Point(lat, lng);
        }
    }

    @GET("utilities/geocode")
    Call<GeocodeResponse> getGeocode(
            @Query("addressText") String addressText,
            @Query("point.lat") Double latitude,
            @Query("point.lng") Double longitude
    );

    @POST("utilities/geocode")
    Call<GeocodeResponse> postGeocode(
            @Body geocodeBody body
    );
}
