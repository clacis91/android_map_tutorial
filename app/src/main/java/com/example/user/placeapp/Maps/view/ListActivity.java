package com.example.user.placeapp.Maps.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.placeapp.Maps.PlaceListContract;
import com.example.user.placeapp.Maps.presenter.ListPresenter;
import com.example.user.placeapp.POJO.sPlace;
import com.example.user.placeapp.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements PlaceListContract.View,
                                                                AdapterView.OnItemClickListener{
    private ListPresenter presenter;
    private ListviewAdapter adapter;

    public class Listviewitem {
        private sPlace place;
        public Listviewitem(sPlace place){
            this.place = place;
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
    public void setMyplaceList(ArrayList<sPlace> places) {
        ListView listView=(ListView)findViewById(R.id.placeList);
        ArrayList<Listviewitem> listviewitems = new ArrayList<>();

        for(sPlace place : places) {
            listviewitems.add(new Listviewitem(place));
        }

        adapter = new ListviewAdapter(this,R.layout.list_content, listviewitems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView poiIdView = view.findViewById(R.id.poiId);
        String poiId = poiIdView.getText().toString();

        Intent i = new Intent(ListActivity.this, PoiActivity.class);
        i.putExtra("poi_id", poiId);
        startActivity(i);
    }

    @Override
    public void setMyplaceList(int position, String poiName) {
        ListView myPlacelistView = (ListView)findViewById(R.id.placeList);
        TextView poiNameView = myPlacelistView.getChildAt(position).findViewById(R.id.poiName);
        poiNameView.setText(poiName);
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
        public String getItem(int position){return data.get(position).toString();}

        @Override
        public long getItemId(int position){return position;}

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = inflater.inflate(layout,parent,false);
            }

            Listviewitem listviewitem = data.get(position);

            ImageView poiImageView = (ImageView)convertView.findViewById(R.id.poiImage);
            TextView poiNameView = (TextView)convertView.findViewById(R.id.poiName);
            TextView poiIdView = (TextView)convertView.findViewById(R.id.poiId);

            Glide.with(context).load(listviewitem.place.getPlacePicUrl().get(0)).into(poiImageView);
            poiNameView.setText("");
            poiIdView.setText(listviewitem.place.getpoiId());

            return convertView;
        }
    }
}
