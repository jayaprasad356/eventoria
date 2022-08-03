package com.example.myapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ChangeAddressActivity;
import com.example.myapplication.Package_listActivity;
import com.example.myapplication.R;
import com.example.myapplication.Successfully_bookedActivity;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.TimeSlots;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TimeSlotsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<TimeSlots> timeSlots;
    Session session;

    public TimeSlotsAdapter(Activity activity, ArrayList<TimeSlots> timeSlots) {
        this.activity = activity;
        this.timeSlots = timeSlots;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSlots1.setSelected(!timeSlots1.isSelected());
                holder.rlTimeslot.setBackgroundResource(timeSlots1.isSelected() ? R.drawable.timeslot_bg2 : R.drawable.timeslot_bg);
                holder.tvPrice.setTextColor(timeSlots1.isSelected() ? ContextCompat.getColor(activity, R.color.primary) : ContextCompat.getColor(activity, R.color.defaulttext));
                holder.tvTimeslot.setTextColor(timeSlots1.isSelected() ? ContextCompat.getColor(activity, R.color.primary) : ContextCompat.getColor(activity, R.color.defaulttext));


//                new AlertDialog.Builder(activity)
//                        .setTitle("Confirm to Booking")
//                        .setMessage("Time Slots \n\nStart Time - "+timeSlots1.getStart_time() + "\nEnd Time - "+timeSlots1.getEnd_time()+"\nPrice - "+timeSlots1.getPrices())
//
//                        // Specifying a listener allows you to take an action before dismissing the dialog.
//                        // The dialog is automatically dismissed when a dialog button is clicked.
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                session.setData(Constant.TIME_SLOT_ID,timeSlots1.getId());
//                                int price = Integer.parseInt(session.getData(Constant.PRICE));
//                                price = price + Integer.parseInt(timeSlots1.getPrices());
//                                session.setData(Constant.PRICE,""+price);
//                                Intent intent = new Intent(activity, Successfully_bookedActivity.class);
//                                intent.putExtra(Constant.TYPE,"venue");
//                                activity.startActivity(intent);
//
//                                // Continue with delete operation
//                            }
//                        })
//
//                        // A null listener allows the button to dismiss the dialog and take no further action.
//                        .setNegativeButton(android.R.string.no, null)
//                        .show();
            }
        });


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

