package com.example.user.placeapp.Maps.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.placeapp.BuildConfig;
import com.example.user.placeapp.Maps.GoogleMapTutorial;
import com.example.user.placeapp.Maps.NearbyPlaces;
import com.example.user.placeapp.Maps.presenter.MainPresenter;
import com.example.user.placeapp.POJO.Nearby;
import com.example.user.placeapp.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMapTutorial.GoogleMapView {
    private PlacesClient placesClient;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private LatLng curPos;
    private HashMap<Marker, String> placeMarkers;
    private ImageView photoView;
    private String googleApiKey;

    private MainPresenter presenter;

    @Override
    public void drawMarker(HashMap<LatLng,String> responseMap) {

        for(LatLng latLng : responseMap.keySet()){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            Marker marker = mMap.addMarker(markerOptions);
            placeMarkers.put(marker,responseMap.get(latLng));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle       savedInstanceState) {
        return inflater.inflate(R.layout.activity_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        googleApiKey = BuildConfig.ApiKey;

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        photoView = getActivity().findViewById(R.id.placePhoto);

        Places.initialize(getContext(), googleApiKey);
        placesClient = Places.createClient(this.getContext());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        placeMarkers = new HashMap<>();

        // For showing a move to my location button
        // mMap.setMyLocationEnabled(true);

        LatLng seoul = new LatLng(37.576208, 126.976818);
        mMap.addMarker(new MarkerOptions().position(seoul).draggable(true)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(seoul).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        curPos = seoul;
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
                CameraPosition cameraPosition = new CameraPosition.Builder().target(newPos).zoom(16).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                for(Marker m : placeMarkers.keySet()) {
                    m.remove();
                }
                placeMarkers.clear();
                getNearbyResponse("museum");
            }
        });

        mMap.setOnMarkerClickListener(this);
    }

    private void getNearbyResponse(String type) {


        presenter.getNearby(curPos,type,googleApiKey);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String clicked_marker_id = marker.getId();
        for(Marker m : placeMarkers.keySet()){
            if(clicked_marker_id.equals(m.getId())) {
                fetchPlace(placeMarkers.get(m));
            }
        }

        return true;
    }

    private void fetchPlace(String placeId) {
        EditText curPosEditText = getActivity().findViewById(R.id.curPos);

        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.PHOTO_METADATAS);
        FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(placeId, placeFields).build();
        Task<FetchPlaceResponse> placeTask = placesClient.fetchPlace(placeRequest);

        Log.d("fetchPlace: ", "SUCCESS");

        placeTask.addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
            @Override
            public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                //responseView.setText(StringUtil.stringify(response, isDisplayRawResultsChecked()));
                // if (isFetchPhotoChecked) {
                Place res_place = fetchPlaceResponse.getPlace();
                curPosEditText.setText(res_place.getName());
                fetchPhoto(res_place);
            }
        });

        placeTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("fetchPlace_res: ", "FAIL");
                e.printStackTrace();
            }
        });
    }

    private void fetchPhoto(Place place) {
        // Get the photo metadata.
        PhotoMetadata photoMetadata = place.getPhotoMetadatas().get(0);

        // Get the attribution text.
        String attributions = photoMetadata.getAttributions();

        // Create a FetchPhotoRequest.
        FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata)
                .setMaxWidth(800) // Optional.
                .setMaxHeight(600) // Optional.
                .build();

        placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
            Log.d("fetchphoto: ", "SUCCESS");
            Bitmap bitmap = fetchPhotoResponse.getBitmap();
            photoView.setImageBitmap(bitmap);
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                int statusCode = apiException.getStatusCode();
                // Handle error with given status code.
                Log.d("fetchphoto", "Place not found: " + exception.getMessage());
            }
        });
    }
}
