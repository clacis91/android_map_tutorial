package com.example.user.placeapp.Maps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.user.placeapp.R;
import com.example.user.placeapp.TabAdapter;

public class PoiActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_PHOTO = 1;
    public static final int REQUEST_ADD_REVIEW = 2;

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String poiId;
    private String fbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi);

        Intent intent = getIntent();
        poiId = intent.getStringExtra("poi_id");
        fbId = intent.getStringExtra("fb_id");

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new OverviewActivity(), "Overview");
        adapter.addFragment(new ReviewActivity(), "Review");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public String getPoiId() {
        return poiId;
    }
    public String getFbId() {
        return fbId;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment;
        switch (requestCode) {
            case REQUEST_ADD_PHOTO :
                fragment = adapter.getItem(0); // Overview
                break;
            case REQUEST_ADD_REVIEW :
                fragment = adapter.getItem(1); // Review
                break;
            default :
                fragment = adapter.getItem(0);
        }
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
