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

    public AutocompleteSupportFragment getAutocompleteSupportFragment() {
        return autocompleteFragment;
    }

    /**
     * TODO :
     * Google map marker -> Place API 연동
     *          1. marker 좌표 얻는 기능 (map fragment상에서 구현) - O
     *          2. marker 정보 main activity로 전달 - O
     *          3. main activity로 넘어온 marker 좌표 출력 (테스트 용) - O
     *          --------------
     *          retrofit 적용해서 nearby api 사용 -- until 190227
     *          4. marker 좌표 이용한 place API 연동
     * History ListView 클릭 : Place API 연동, Google map marker 이동
     *  --- > History가 아닌 각 marker의 브리핑 정보를 보여주도록
     *        왼편은 detail 정보
     * Google map 상단 검색창
     */


}
