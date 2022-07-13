package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.helper.Constant;

public class PackagedetailsActivity extends AppCompatActivity {

    Button start_booking_btn;
    ImageView back_btn;
    String getPackage_name,getPackage_img,getPackage_price,package_desc;
    ImageView imgPackage;
    TextView tvPack_name,tvPack_price,tvDescription;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packagedetails);
        activity = PackagedetailsActivity.this;


        getPackage_img = getIntent().getStringExtra(Constant.IMAGE_PACKAGE);
        getPackage_name = getIntent().getStringExtra(Constant.PACKAGE_NAME);
        getPackage_price = getIntent().getStringExtra(Constant.PACKAGE_PRICE);
        package_desc = getIntent().getStringExtra(Constant.PACKAGE_DESCRIPTION);



        start_booking_btn = findViewById(R.id.start_booking_btn);
        back_btn = findViewById(R.id.back_btn);
        imgPackage = findViewById(R.id.imgPackage);
        tvPack_name = findViewById(R.id.tvPack_name);
        tvPack_price = findViewById(R.id.tvPack_price);
        tvDescription = findViewById(R.id.tvDescription);

        tvPack_name.setText(""+getPackage_name);
        tvPack_price.setText("Rs. "+getPackage_price);
        tvDescription.setText(package_desc);
        Glide.with(activity).load(getPackage_img).into(imgPackage);


        start_booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PackagedetailsActivity.this,CheckoutActivity.class);
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
}