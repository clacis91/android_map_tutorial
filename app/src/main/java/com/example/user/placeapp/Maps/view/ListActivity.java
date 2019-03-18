package com.example.user.placeapp.Maps.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.Maps.placeListContract;
import com.example.user.placeapp.Maps.presenter.ListPresenter;
import com.example.user.placeapp.POJO.sPlace;
import com.example.user.placeapp.R;
import com.kt.place.sdk.net.PoiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity implements placeListContract.View{
    private ListPresenter presenter;
    private ListviewAdapter adapter;

    public class Listviewitem {
        private sPlace place;
        private String poiId;
        public Listviewitem(sPlace place, String poiId){
            this.place = place;
            this.poiId = poiId;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String fbId = intent.getStringExtra("fb_id");

        Log.d("facebookId", fbId);

        presenter = new ListPresenter(this);
        presenter.getMyplaceList(fbId);
    }

    @Override
    public void setMyplaceList(HashMap<sPlace, String> response) {
        ListView listView=(ListView)findViewById(R.id.placeList);
        ArrayList<Listviewitem> listviewitems = new ArrayList<>();

        for(sPlace item : response) {
            listviewitems.add(new Listviewitem(item));
        } 

        adapter = new ListviewAdapter(this,R.layout.list_content, listviewitems);
        listView.setAdapter(adapter);
    }

    public class ListviewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int layout;
        private Context context;

        private ArrayList<Listviewitem> data;
        private String poiName;

        public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data){
            this.context = context;
            this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data=data;
            this.layout=layout;
        }

        @Override
        public int getCount(){return data.size();}

        @Override
        public String getItem(int position){return data.get(position).place.get_id();}

        @Override
        public long getItemId(int position){return position;}

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView==null){
                convertView=inflater.inflate(layout,parent,false);
            }
            Listviewitem listviewitem = data.get(position);
            ImageView poiImageView=(ImageView)convertView.findViewById(R.id.poiImage);
            Glide.with(context).load(listviewitem.place.getPlacePicUrl().get(0)).into(poiImageView);
            TextView poiNameView = (TextView)convertView.findViewById(R.id.poiName);
            //poiNameView.setText(listviewitem.place.get());
            return convertView;
        }
    }

}
