package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.SliderAdapterExample;
import com.example.myapplication.adapter.TimeSlotsAdapter;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.Slide;
import com.example.myapplication.model.TimeSlots;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONObject;

import java.util.ArrayList;

public class VenuedetailsActivity extends AppCompatActivity {


    TextView tvVenuename,tvAddress;
    String Venuename,VenueAddress,Venueimg,image1,image2,image3,image4;;
    Activity activity;
    TimeSlotsAdapter timeSlotsAdapter;
    RecyclerView recyclerView;
    ArrayList<TimeSlots> timeSlots = new ArrayList<>();
    SliderView sliderView;
    private SliderAdapterExample adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venuedetails);
        activity = VenuedetailsActivity.this;
        sliderView = findViewById(R.id.image_slider);
        Venuename = getIntent().getStringExtra(Constant.VENUE_NAME);
        VenueAddress = getIntent().getStringExtra(Constant.VENUE_ADDRESS);
        Venueimg = getIntent().getStringExtra(Constant.VENUE_IMG);
        recyclerView = findViewById(R.id.recyclerView);
        tvAddress = findViewById(R.id.tvAddress);
        tvVenuename = findViewById(R.id.tvVenuename);
        image1 = getIntent().getStringExtra(Constant.IMAGE1);
        image2 = getIntent().getStringExtra(Constant.IMAGE2);
        image3 = getIntent().getStringExtra(Constant.IMAGE3);
        image4 = getIntent().getStringExtra(Constant.IMAGE4);
        adapter = new SliderAdapterExample(VenuedetailsActivity.this);
        slideslist();
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        tvVenuename.setText(Venuename);
        tvAddress.setText(VenueAddress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        timeSlots = (ArrayList<TimeSlots>) args.getSerializable("ARRAYLIST");
        timeslotList();
    }

    private void timeslotList()
    {

        timeSlotsAdapter = new TimeSlotsAdapter(activity, timeSlots);
        recyclerView.setAdapter(timeSlotsAdapter);
    }
    private void slideslist() {
        ArrayList<Slide> slides = new ArrayList<>();
        Slide coverimg = new Slide("1","",Venueimg,"");
        Slide slide1 = new Slide("1","",image1,"");
        Slide slide2 = new Slide("1","",image2,"");
        Slide slide3 = new Slide("1","",image3,"");
        Slide slide4 = new Slide("1","",image4,"");
        if (!Venueimg.equals("")){
            slides.add(coverimg);

        }
        if (!image1.equals("")){
            slides.add(slide1);

        }
        if (!image2.equals("")){
            slides.add(slide2);

        }
        if (!image3.equals("")){
            slides.add(slide3);

        }
        if (!image4.equals("")){
            slides.add(slide4);
        }

        adapter.renewItems(slides);

    }
}