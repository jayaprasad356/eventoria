package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Package_listActivity;
import com.example.myapplication.R;
import com.example.myapplication.VenuedetailsActivity;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.TimeSlots;
import com.example.myapplication.model.Venue;

import java.io.Serializable;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VenueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Venue> venues;
    Session session;

    public VenueAdapter(Activity activity, ArrayList<Venue> venues) {
        this.activity = activity;
        this.venues = venues;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.venue_items, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Venue venue = venues.get(position);

        Glide.with(activity).load(venue.getCover_image()).into(holder.imgVenue);
        holder.tvVenuename.setText(venue.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("VENUE_ARR",venue.getTimeslots().toString());
//                Intent intent = new Intent(activity, VenuedetailsActivity.class);
//                intent.putExtra("QuestionListExtra", venue.getTimeslots());
//                activity.startActivity(intent);

                session.setData(Constant.VENUE_ID,venue.getId());

                Intent intent = new Intent(activity, VenuedetailsActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)venue.getTimeslots());
                intent.putExtra("BUNDLE",args);
                intent.putExtra(Constant.VENUE_NAME,venue.getName());
                intent.putExtra(Constant.VENUE_ADDRESS,venue.getAddress());
                intent.putExtra(Constant.VENUE_IMG,venue.getCover_image());
                intent.putExtra(Constant.IMAGE1,venue.getImage1());
                intent.putExtra(Constant.IMAGE2,venue.getImage2());
                intent.putExtra(Constant.IMAGE3,venue.getImage3());
                intent.putExtra(Constant.IMAGE4,venue.getImage4());
                activity.startActivity(intent);





            }
        });
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final ImageView imgVenue;
        final TextView tvVenuename;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            imgVenue = itemView.findViewById(R.id.imgVenue);
            tvVenuename = itemView.findViewById(R.id.tvVenuename);


        }
    }
}

