package com.example.user.placeapp.Maps;

import com.example.user.placeapp.POJO.sComment;

import java.util.List;

public interface ReviewContract {

    interface Presenter {

        public void getReviewList(String poiId);
    }

    interface View {

        public void setReviewList(List<sComment> comments);
    }
}
