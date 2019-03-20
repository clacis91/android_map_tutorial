package com.example.user.placeapp.Maps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.placeapp.Maps.PoiContract;
import com.example.user.placeapp.Maps.presenter.PoiPresenter;
import com.example.user.placeapp.POJO.sPlaceReview;
import com.example.user.placeapp.R;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.kt.place.sdk.model.Poi;

import java.util.List;

public class PoiActivity extends AppCompatActivity implements PoiContract.View {

    PoiPresenter presenter;
    private String poiId;
    private SliderLayout mDemoSlider;

    public static final int REQUEST_ADD_PHOTO = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi);

        Intent intent = getIntent();
        poiId = intent.getStringExtra("poi_id");

        presenter = new PoiPresenter(this);
        presenter.getPoiInfo(poiId);

        mDemoSlider = findViewById(R.id.poiImageSlider);
    }

    @Override
    public void setPoiImage(List<String> poiImages) {
        ImageView imageView = findViewById(R.id.poiImage);
        //Glide.with(this).load(poiImage).into(imageView);
        mDemoSlider.removeAllSliders();

        for(String poiImage : poiImages) {
            TextSliderView sliderView = new TextSliderView(this);

            sliderView.image(poiImage)
                    .setProgressBarVisible(true);
            mDemoSlider.addSlider(sliderView);
        }
        //mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    }

    @Override
    public void setPoiInfo(Poi place) {
        String placeName = place.getName() + " " + place.getBranch();
        TextView placeNameView = findViewById(R.id.poiName);
        placeNameView.setText(placeName);
    }

    public void addPhoto(View v) {
        Intent i = new Intent(PoiActivity.this, AddPhotoActivity.class);
        i.putExtra("poi_id", poiId);
        startActivityForResult(i, REQUEST_ADD_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_PHOTO && resultCode == RESULT_OK) {
            sPlaceReview review = (sPlaceReview) data.getSerializableExtra("place_review");
            setPoiImage(review.getPlacePicUrl());
        }
    }
}
