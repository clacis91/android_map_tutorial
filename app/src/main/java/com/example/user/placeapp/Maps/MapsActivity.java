package com.example.user.placeapp.Maps;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.user.placeapp.Position;
import com.example.user.placeapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import lombok.*;

public class MapsActivity extends Fragment implements OnMapReadyCallback {
    private PlacesClient placesClient;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    //private Position curPos;
    private LatLng curPos;

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

        Places.initialize(this.getContext(), "AIzaSyDqjr cHNDdH52OgRJEGoUl4j5iiCmNg9GY");
        PlacesClient mplaceClient = Places.createClient(this.getContext());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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

                String posStr = curPos.toString();
                //EditText curPosEditText = getActivity().findViewById(R.id.curPos);
                //curPosEditText.setText(posStr);

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Create URL
                        try {
                            URL geocodeURL = new URL("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyDqjrcHNDdH52OgRJEGoUl4j5iiCmNg9GY&latlng=" + String.valueOf(curPos.latitude) + "," + String.valueOf(curPos.longitude) + "&sensor=true");

                            // Create connection
                            HttpsURLConnection myConnection = (HttpsURLConnection) geocodeURL.openConnection();
                            // All your networking logic
                            // should be here
                            InputStream in = new BufferedInputStream(myConnection.getInputStream());
                            JSONObject json = new JSONObject(getStringFromInputStream(in));
                            Log.d("looooog", json.toString());
                        } catch (Exception e) {
                            Log.d("looooog", e.toString());
                        }
                    }
                });
            }
        });
    }

    private static String getStringFromInputStream(InputStream is) {



        BufferedReader br = null;

        StringBuilder sb = new StringBuilder();



        String line;

        try {



            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {

                sb.append(line);

            }



        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (br != null) {

                try {

                    br.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }



        return sb.toString();



    }
}
