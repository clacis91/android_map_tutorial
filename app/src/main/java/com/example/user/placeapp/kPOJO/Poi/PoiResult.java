package com.example.user.placeapp.kPOJO.Poi;

import java.util.List;

public class PoiResult {

    class PoiDetail {
        private String id;
        private Address address;
        private String name;
        private String branch;
        private Category category;
        private Point point;
        private Phones phones;
        //private List<Child> children = null;
        //private Extension extension;
        //private List<Theme> theme = null;
    }

    private Integer numberOfPois;
    private Integer numberOfAddress;
    private List<PoiDetail> pois = null;

}
