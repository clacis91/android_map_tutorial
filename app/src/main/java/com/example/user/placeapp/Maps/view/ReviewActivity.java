package com.example.user.placeapp.Maps.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.placeapp.Maps.ReviewContract;
import com.example.user.placeapp.Maps.presenter.ReviewPresenter;
import com.example.user.placeapp.POJO.sComment;
import com.example.user.placeapp.POJO.sPlaceWithComment;
import com.example.user.placeapp.POJO.sUser;
import com.example.user.placeapp.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends Fragment implements ReviewContract.View {

    public static final int REQUEST_ADD_REVIEW = 2;
    public static final int RESULT_OK = -1;

    private ReviewPresenter presenter;
    private ReviewActivity.ListviewAdapter adapter;

    private String poiId;
    private String fbId;


    public class Listviewitem {
        private sUser user;
        private String commentTitle;
        private String commentBody;

        public Listviewitem(sUser user, String commentTitle, String commentBody){
            this.user = user;
            this.commentTitle = commentTitle;
            this.commentBody = commentBody;
        }

        public String getCommentTitle() {
            return commentTitle;
        }

        public String getCommentBody() {
            return commentBody;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PoiActivity poiActivity = (PoiActivity) getActivity();
        poiId = poiActivity.getPoiId();
        fbId = poiActivity.getFbId();

        presenter = new ReviewPresenter(this);
        presenter.getReviewList(poiId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_review, container, false);

        final Button addReview = (Button) v.findViewById(R.id.addReview);
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getActivity(), AddReviewActivity.class);
                i.putExtra("poi_id", poiId);
                i.putExtra("fb_id", fbId);
                getActivity().startActivityForResult(i, REQUEST_ADD_REVIEW);
            }
        });

        return v;
    }

    @Override
    public void setReviewList(List<sComment> comments) {
        ListView listView=(ListView) getView().findViewById(R.id.reviewList);
        ArrayList<Listviewitem> listviewitems = new ArrayList<>();

        for(sComment comment : comments) {
            sUser userProfile = comment.getUser();
            String captionTitle = comment.getCaptionTitle();
            String captionBody = comment.getCaptionBody();

            listviewitems.add(new Listviewitem(userProfile, captionTitle, captionBody));
        }

        adapter = new ListviewAdapter(getContext(),R.layout.review_list_content, listviewitems);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_REVIEW && resultCode == RESULT_OK) {
            sPlaceWithComment review = (sPlaceWithComment) data.getSerializableExtra("place_review");
            setReviewList(review.getComments());
        }
    }

    public class ListviewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int layout;
        private Context context;

        private ArrayList<ReviewActivity.Listviewitem> data;

        public ListviewAdapter(Context context, int layout, ArrayList<ReviewActivity.Listviewitem> data){
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

            ReviewActivity.Listviewitem listviewitem = data.get(position);

            ImageView userImageView = (ImageView)convertView.findViewById(R.id.userImage);
            TextView userNameView = (TextView)convertView.findViewById(R.id.userName);

            TextView commentTitleView = (TextView)convertView.findViewById(R.id.commentTitle);
            TextView commentBodyView = (TextView)convertView.findViewById(R.id.commentBody);

            Glide.with(context).load(listviewitem.user.getUserProfileUrl()).into(userImageView);
            userNameView.setText(listviewitem.user.getName());

            commentTitleView.setText(listviewitem.getCommentTitle());
            commentBodyView.setText(listviewitem.getCommentBody());

            return convertView;
        }
    }
}
