package com.example.user.placeapp.Maps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.placeapp.POJO.Nearby;
import com.example.user.placeapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends Fragment implements OnMapReadyCallback {
    //private PlacesClient placesClient;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private LatLng curPos;
    private ArrayList<Marker> placeMarkers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle       savedInstanceState) {
        return inflater.inflate(R.layout.activity_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Places.initialize(this.getContext(), "AIzaSyDqjr cHNDdH52OgRJEGoUl4j5iiCmNg9GY");
        //placesClient  = Places.createClient(this.getContext());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        placeMarkers = new ArrayList<>();

        // For showing a move to my location button
        // mMap.setMyLocationEnabled(true);

        // For dropping a marker at a point on the Map
        LatLng sydney = new LatLng(-34, 151);
        //Marker marker = new Marker();
        mMap.addMarker(new MarkerOptions().position(sydney).draggable(true));
        //curPos = new Position();

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

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
                CameraPosition cameraPosition = new CameraPosition.Builder().target(newPos).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                if(placeMarkers.size() > 0) {
                    for (int i = 0; i < placeMarkers.size(); i++) {
                        placeMarkers.get(i).remove();
                    }
                    placeMarkers.clear();
                }

                String posStr = curPos.toString();
                getNearbyResponse("restaurant");
                //EditText curPosEditText = getActivity().findViewById(R.id.curPos);
                //curPosEditText.setText(posStr);
            }
        });
    }

    private void getNearbyResponse(String type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NearbyPlaces nearbyPlaces = retrofit.create(NearbyPlaces.class);

        Call<Nearby> call = nearbyPlaces.getNearbyPlaces(String.valueOf(curPos.latitude) + "," + String.valueOf(curPos.longitude), 1500, type);

        call.enqueue(new Callback<Nearby>() {
            @Override
            public void onResponse(Call<Nearby> call, Response<Nearby> response) {
                if (!response.isSuccessful()) {
                    Log.d("response_fail", response.toString());
                    return;
                }

                try {
                    // 이 method는 분리
                    // This loop will go through all the results and add marker on each location.
                    Log.d("onResponse", response.body().getResults().toString());
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        Double lat = response.body().getResults().get(i).getGeometry().getLocation().getLat();
                        Double lng = response.body().getResults().get(i).getGeometry().getLocation().getLng();
                        //String placeName = response.body().getResults().get(i).getName();
                        //String vicinity = response.body().getResults().get(i).getVicinity();
                        LatLng latlng = new LatLng(lat, lng);

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latlng);
                        // Adding Title to the Marker
                        //markerOptions.title(placeName + " : " + vicinity);
                        Marker m = mMap.addMarker(markerOptions);
                        placeMarkers.add(m);
                    }
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Nearby> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}
