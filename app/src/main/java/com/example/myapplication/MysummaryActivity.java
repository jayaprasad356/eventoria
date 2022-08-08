package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;

public class MysummaryActivity extends AppCompatActivity {
    ImageView ivProductimg;
    String getPackage_name,getPackage_img,getPackage_price,getAddress;
    TextView tvProductname,tvPrice,tvadresss,tvProductprice,tvTotalprice;
    Session session;
    Activity activity;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysummary);
        activity = MysummaryActivity.this;
        session = new Session(activity);


        getPackage_img = session.getData(Constant.PACKAGE_IMAGE);
        getPackage_name = session.getData(Constant.PACKAGE_NAME);
        getPackage_price = session.getData(Constant.PACKAGE_PRICE);
        getAddress = getIntent().getStringExtra(Constant.ADDRESS);

        ivProductimg = findViewById(R.id.ivProductimg);
        tvProductname = findViewById(R.id.tvProductname);
        tvPrice = findViewById(R.id.tvPrice);
        tvTotalprice = findViewById(R.id.tvTotalPrice);
        tvadresss = findViewById(R.id.tvadresss);
        tvProductprice = findViewById(R.id.tvProductprice);
        btnConfirm = findViewById(R.id.btnConfirm);


        Glide.with(activity).load(getPackage_img).into(ivProductimg);
        tvProductname.setText(getPackage_name);
        tvPrice.setText("₹"+getPackage_price);
        tvProductname.setText("₹"+getPackage_name);
        tvadresss.setText(getAddress);
        tvTotalprice.setText("₹"+getPackage_price);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MysummaryActivity.this,Successfully_bookedActivity.class);
                intent.putExtra(Constant.TYPE,"own");
                startActivity(intent);
            }
        });

    }
}