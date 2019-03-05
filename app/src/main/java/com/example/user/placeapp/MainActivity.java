package com.example.user.placeapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.user.placeapp.Maps.view.MapsActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public class MainActivity extends AppCompatActivity {
    AutocompleteSupportFragment autocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the AutocompleteSupportFragment.
        autocompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapLayout, new MapsActivity()).commit();
    }

    public AutocompleteSupportFragment getAutocompleteFragment() {
        return autocompleteFragment;
    }
}
