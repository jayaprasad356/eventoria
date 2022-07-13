package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
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
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Venue;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VenueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Venue> venues;

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
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Venue venue = venues.get(position);

        Glide.with(activity).load(venue.getCover_image()).into(holder.imgVenue);
        holder.tvVenuename.setText(venue.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(activity, VenuedetailsActivity.class);
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

