package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.adapter.SliderAdapterExample;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Slide;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class PackagedetailsActivity extends AppCompatActivity {

    Button start_booking_btn;
    ImageView back_btn;
    String getPackage_name,getPackage_img,getPackage_price,package_desc,image1,image2,image3,image4;
    TextView tvPack_name,tvPack_price,tvDescription;
    Activity activity;
    SliderView sliderView;
    private SliderAdapterExample adapter;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packagedetails);
        activity = PackagedetailsActivity.this;
        session = new Session(activity);
        sliderView = findViewById(R.id.image_slider);
        getPackage_img = getIntent().getStringExtra(Constant.IMAGE_PACKAGE);
        getPackage_name = getIntent().getStringExtra(Constant.PACKAGE_NAME);
        getPackage_price = getIntent().getStringExtra(Constant.PACKAGE_PRICE);
        package_desc = getIntent().getStringExtra(Constant.PACKAGE_DESCRIPTION);
        image1 = getIntent().getStringExtra(Constant.IMAGE1);
        image2 = getIntent().getStringExtra(Constant.IMAGE2);
        image3 = getIntent().getStringExtra(Constant.IMAGE3);
        image4 = getIntent().getStringExtra(Constant.IMAGE4);
        start_booking_btn = findViewById(R.id.start_booking_btn);
        back_btn = findViewById(R.id.back_btn);
        tvPack_name = findViewById(R.id.tvPack_name);
        tvPack_price = findViewById(R.id.tvPack_price);
        tvDescription = findViewById(R.id.tvDescription);
        adapter = new SliderAdapterExample(PackagedetailsActivity.this);
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


        tvPack_name.setText(""+getPackage_name);
        tvPack_price.setText("Rs. "+getPackage_price);
        tvDescription.setText(package_desc);
        start_booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PackagedetailsActivity.this,CheckoutActivity.class);
                session.setData(Constant.PACKAGE_IMAGE,image1);
                session.setData(Constant.PACKAGE_NAME,getPackage_name);
                session.setData(Constant.PACKAGE_PRICE,getPackage_price);
                startActivity(intent);
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void slideslist() {
        ArrayList<Slide> slides = new ArrayList<>();
        Slide coverimg = new Slide("1","",getPackage_img,"","","","","");
        Slide slide1 = new Slide("1","",image1,"","","","","");
        Slide slide2 = new Slide("1","",image2,"","","","","");
        Slide slide3 = new Slide("1","",image3,"","","","","");
        Slide slide4 = new Slide("1","",image4,"","","","","");
        Log.d("slide_img1",image1);
        Log.d("slide_img1",image2);
        if (!getPackage_img.equals("")){
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