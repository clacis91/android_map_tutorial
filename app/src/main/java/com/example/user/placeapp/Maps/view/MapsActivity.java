package com.example.user.placeapp.Maps.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.placeapp.BuildConfig;
import com.example.user.placeapp.MainActivity;
import com.example.user.placeapp.Maps.GoogleMapContract;
import com.example.user.placeapp.Maps.presenter.MapsPresenter;
import com.example.user.placeapp.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.kt.place.sdk.util.Client;
//import com.kt.place.sdk.util.Manager;

import java.util.Arrays;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback/*,
                                                        GoogleMap.OnMapClickListener,
                                                        GoogleMap.OnMarkerClickListener,
                                                        GoogleMapContract.View */{
    private MapsPresenter presenter;

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private ImageView photoView;

    //private Client placeClient;
    private String apiKey;

    private LatLng curPos;
    private Marker curMarker;
    private HashMap<Marker, String> placeMarkers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        //String fbId = intent.getStringExtra("fb_id");
        String accessToken = intent.getStringExtra("fb_token");

        Log.d("access_token", accessToken);
        // TODO : 로그인한 유저 정보를 MainActivity intent에서 받기

        //presenter = new MapsPresenter(this);
        apiKey = BuildConfig.ApiKey;

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Manager.initialize(getContext(), apiKey);
        //placeClient = new Client();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /*placeMarkers = new HashMap<>();

        LatLng seoul = new LatLng(37.576208, 126.976818);
        curPos = seoul;
        curMarker = mMap.addMarker(new MarkerOptions().position(seoul).draggable(true));
        curMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        moveCamera(seoul);

        getNearbyResponse("museum");

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng newPos = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
                curPos = newPos;
                moveCamera(curPos);

                for(Marker m : placeMarkers.keySet()) {
                    m.remove();
                }
                placeMarkers.clear();
                getNearbyResponse("museum");
            }
        });

        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);*/
    }

    /*

    @Override
    public void onMapClick(LatLng latlng) {
        moveCurPosition(latlng);
    }

    private void moveCurPosition(LatLng latlng) {
        curPos = latlng;
        curMarker.setPosition(latlng);
        moveCamera(curPos);

        for(Marker m : placeMarkers.keySet()) {
            m.remove();
        }
        placeMarkers.clear();
        getNearbyResponse("museum");
    }

    private void moveCamera(LatLng position) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void getNearbyResponse(String type) {
        //presenter.getNearby(curPos, type);
    }

    //@Override
    public void drawNearbyMarker(HashMap<LatLng,String> responseMap) {
        for(LatLng latlng : responseMap.keySet()){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latlng);
            Marker marker = mMap.addMarker(markerOptions);
            placeMarkers.put(marker,responseMap.get(latlng));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String clickedMarkerId = marker.getId();
        for(Marker m : placeMarkers.keySet()){
            if(clickedMarkerId.equals(m.getId())) {
                getPlaceInfo(placeMarkers.get(m));
            }
        }

        return true;
    }

    private void getPlaceInfo(String placeId) {
        //presenter.getPlaceInfo(placesClient, placeId);
    }

    //@Override
    public void drawPlaceInfo(Bitmap placePhoto) {
        photoView.setImageBitmap(placePhoto);
    }
    */
}
