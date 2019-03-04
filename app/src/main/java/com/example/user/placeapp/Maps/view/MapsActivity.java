package com.example.user.placeapp.Maps.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.HashMap;

public class MapsActivity extends Fragment implements OnMapReadyCallback,
                                                        GoogleMap.OnMapClickListener,
                                                        GoogleMap.OnMarkerClickListener,
                                                        GoogleMapContract.View {
    private MapsPresenter presenter;

    private SupportMapFragment mapFragment;
    private ImageView photoView;
    private String googleApiKey;

    private PlacesClient placesClient;
    private GoogleMap mMap;

    private LatLng curPos;
    private Marker curMarker;
    private HashMap<Marker, String> placeMarkers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MapsPresenter(this);
        googleApiKey = BuildConfig.ApiKey;
        placeMarkers = new HashMap<>();
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        photoView = getActivity().findViewById(R.id.placePhoto);

        Places.initialize(getContext(), googleApiKey);
        placesClient = Places.createClient(this.getContext());

        MainActivity mainActivity = (MainActivity) getActivity();
        AutocompleteSupportFragment autocompleteFragment = mainActivity.getAutocompleteFragment();
        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG));
        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                moveCurPosition(place.getLatLng());
            }

            @Override
            public void onError(Status status) {
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // For showing a move to my location button
        // mMap.setMyLocationEnabled(true);

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
        mMap.setOnMarkerClickListener(this);
    }

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

    private void getNearbyResponse(String type) {
        presenter.getNearby(curPos, type, googleApiKey);
    }

    @Override
    public void drawMarker(HashMap<LatLng,String> responseMap) {
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
        presenter.getPlaceInfo(placesClient, placeId);
    }

    @Override
    public void drawPlaceInfo(Place place, Bitmap placePhoto) {
        TextView placeNameTextView = getActivity().findViewById(R.id.placeName);
        placeNameTextView.setText(place.getName());
        TextView placeAddressTextView = getActivity().findViewById(R.id.placeAddress);
        placeAddressTextView.setText(place.getAddress());
        photoView.setImageBitmap(placePhoto);
    }

    private void moveCamera(LatLng position) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
