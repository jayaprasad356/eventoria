package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.TimeSlotsAdapter;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.TimeSlots;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class VenuedetailsActivity extends AppCompatActivity {

    Button start_booking_btn;
    ImageView imgVenue;
    TextView tvVenuename,tvAddress;
    String Venuename,VenueAddress,Venueimg;
    Activity activity;
    TimeSlotsAdapter timeSlotsAdapter;
    RecyclerView recyclerView;
    ArrayList<TimeSlots> timeSlots = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venuedetails);
        activity = VenuedetailsActivity.this;
        Venuename = getIntent().getStringExtra(Constant.VENUE_NAME);
        VenueAddress = getIntent().getStringExtra(Constant.VENUE_ADDRESS);
        Venueimg = getIntent().getStringExtra(Constant.VENUE_IMG);
        recyclerView = findViewById(R.id.recyclerView);
        start_booking_btn = findViewById(R.id.start_booking_btn);
        tvAddress = findViewById(R.id.tvAddress);
        imgVenue = findViewById(R.id.imgVenue);
        tvVenuename = findViewById(R.id.tvVenuename);
        tvVenuename.setText(Venuename);
        tvAddress.setText(VenueAddress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        Glide.with(activity).load(Venueimg).into(imgVenue);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        timeSlots = (ArrayList<TimeSlots>) args.getSerializable("ARRAYLIST");
        start_booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VenuedetailsActivity.this,Successfully_bookedActivity.class);
                startActivity(intent);
            }
        });
        timeslotList();
    }

    private void timeslotList()
    {
        Log.d("VENUE_SIZE",""+timeSlots.size());

        timeSlotsAdapter = new TimeSlotsAdapter(activity, timeSlots);
        recyclerView.setAdapter(timeSlotsAdapter);
    }
}