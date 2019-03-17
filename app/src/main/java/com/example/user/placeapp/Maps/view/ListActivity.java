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
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.placeapp.Maps.model.MapServiceModel;
import com.example.user.placeapp.POJO.sPlace;
import com.example.user.placeapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        //String = intent.getStringExtra("");

        //Log.d("access_token", "");

        MapServiceModel test = new MapServiceModel();
        List<sPlace> testPlace = test.CallPlaceList();

        ListView listView=(ListView)findViewById(R.id.placeList);
        ArrayList<Listviewitem> listviewitems = new ArrayList<>();
        listviewitems.add(new Listviewitem(testPlace.get(0)));
        listviewitems.add(new Listviewitem(testPlace.get(1)));

        ListviewAdapter adapter=new ListviewAdapter(this,R.layout.list_content, listviewitems);
        listView.setAdapter(adapter);
    }

    public class Listviewitem {
        private sPlace place;
        public Listviewitem(sPlace place){
            this.place = place;
        }
    }

    public class ListviewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<Listviewitem> data;
        private int layout;
        public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data){
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
            //ImageView icon=(ImageView)convertView.findViewById(R.id.imageview);
            //icon.setImageResource(listviewitem.getIcon());
            TextView content1 = (TextView)convertView.findViewById(R.id.content1);
            TextView content2 = (TextView)convertView.findViewById(R.id.content2);

            content1.setText(listviewitem.place.getPlacePicUrl().get(0));
            content2.setText(listviewitem.place.getpoiId());
            return convertView;
        }
    }

}
