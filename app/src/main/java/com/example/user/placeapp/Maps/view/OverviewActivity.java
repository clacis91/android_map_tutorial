package com.example.user.placeapp.Maps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.placeapp.Maps.OverviewContract;
import com.example.user.placeapp.Maps.presenter.OverviewPresenter;
import com.example.user.placeapp.POJO.sPlaceOverview;
import com.example.user.placeapp.R;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.kt.place.sdk.model.Poi;

import java.util.List;

public class OverviewActivity extends Fragment implements OverviewContract.View {

    public static final int REQUEST_ADD_PHOTO = 1;
    public static final int RESULT_OK = -1;
    public static final String TAG = PoiActivity.class.getName();

    OverviewPresenter presenter;
    private String poiId;
    private SliderLayout mDemoSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_overview, container, false);
        mDemoSlider = v.findViewById(R.id.poiImageSlider);

        final Button addPhoto = (Button) v.findViewById(R.id.addPhoto);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getActivity(), AddPhotoActivity.class);
                i.putExtra("poi_id", poiId);
                getActivity().startActivityForResult(i, REQUEST_ADD_PHOTO);
            }
        });

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PoiActivity poiActivity = (PoiActivity) getActivity();
        poiId = poiActivity.getPoiId();

        presenter = new OverviewPresenter(this);
        presenter.getOverviewInfo(poiId);
    }

    @Override
    public void setOverviewImage(List<String> poiImages) {
        ImageView imageView = getView().findViewById(R.id.poiImage);
        mDemoSlider.removeAllSliders();

        for(String poiImage : poiImages) {
            TextSliderView sliderView = new TextSliderView(getContext());

            Log.d(TAG, "setOverviewImage: " + poiImage);
            sliderView.image(poiImage)
                    .setProgressBarVisible(true);
            mDemoSlider.addSlider(sliderView);
        }
        //mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    }

    @Override
    public void setOverviewInfo(Poi place) {
        String placeName = place.getName() + " " + place.getBranch();
        TextView placeNameView = getView().findViewById(R.id.poiName);
        placeNameView.setText(placeName);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_PHOTO && resultCode == RESULT_OK) {
            sPlaceOverview review = (sPlaceOverview) data.getSerializableExtra("place_overview");
            setOverviewImage(review.getPlacePicUrl());
        }
    }
}