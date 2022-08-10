package com.example.myapplication.adapter;

import static com.example.myapplication.VenuedetailsActivity.slotday;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Package_listActivity;
import com.example.myapplication.R;
import com.example.myapplication.VenuedetailsActivity;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.DateSlot;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class DateSlotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<DateSlot> dateSlots;
    int selected_position;



    public DateSlotAdapter(Activity activity, ArrayList<DateSlot> dateSlots) {
        this.activity = activity;
        this.dateSlots = dateSlots;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.date_item, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final DateSlot dateSlot = dateSlots.get(position);
        holder.tvDate.setText(dateSlot.getSlotDate());
        if (position == selected_position) {
            holder.dateCard.setStrokeColor(ContextCompat.getColor(activity,R.color.secodary));
            holder.tvDate.setTextColor(ContextCompat.getColor(activity, R.color.secodary));
            slotday = dateSlot.getDateft();
        } else {
            holder.dateCard.setStrokeColor(ContextCompat.getColor(activity,R.color.black));
            holder.tvDate.setTextColor(ContextCompat.getColor(activity, R.color.defaulttext));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_position = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dateSlots.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {
        final TextView tvDate;
        final MaterialCardView dateCard;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            dateCard = itemView.findViewById(R.id.dateCard);


        }
    }
}

