package com.example.user.placeapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.user.placeapp.Maps.MapsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapLayout, new MapsActivity()).commit();
    }

    /**
     * TODO : Layout 분할 (좌측 : Place API info / 우측 : History)
     * Google map marker -> Place API 연동
     *          1. marker 좌표 얻는 기능 (map fragment상에서 구현) - O
     *          2. marker 정보 main activity로 전달 - O
     *          3. main activity로 넘어온 marker 좌표 출력 (테스트 용) - O
     *          4. marker 좌표 이용한 place API 연동
     *          --------- Until 0225
     * History ListView 클릭 : Place API 연동, Google map marker 이동
     * Google map 상단 검색창
     */


}
