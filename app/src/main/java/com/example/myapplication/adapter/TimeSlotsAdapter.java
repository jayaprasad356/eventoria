package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.TimeSlots;

import java.util.ArrayList;

public class TimeSlotsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<TimeSlots> timeSlots;
    Session session;
    String type;

    public TimeSlotsAdapter(Activity activity, ArrayList<TimeSlots> timeSlots, String type) {
        this.activity = activity;
        this.timeSlots = timeSlots;
        this.type = type;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.timeslot_item, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final TimeSlots timeSlots1 = timeSlots.get(position);

        holder.tvTimeslot.setText(timeSlots1.getStart_time() +" - "+timeSlots1.getEnd_time());
        holder.tvPrice.setText("Rs. "+timeSlots1.getPrices());
        if (type.equals("venue")){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timeSlots1.setSelected(!timeSlots1.isSelected());
                    holder.rlTimeslot.setBackgroundResource(timeSlots1.isSelected() ? R.drawable.timeslot_bg2 : R.drawable.timeslot_bg);
                    holder.tvPrice.setTextColor(timeSlots1.isSelected() ? ContextCompat.getColor(activity, R.color.primary) : ContextCompat.getColor(activity, R.color.defaulttext));
                    holder.tvTimeslot.setTextColor(timeSlots1.isSelected() ? ContextCompat.getColor(activity, R.color.primary) : ContextCompat.getColor(activity, R.color.defaulttext));


                }
            });

        }




    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final TextView tvTimeslot,tvPrice;
        RelativeLayout rlTimeslot;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvTimeslot = itemView.findViewById(R.id.tvTimeslot);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            rlTimeslot = itemView.findViewById(R.id.rlTimeslot);


        }
    }
}

