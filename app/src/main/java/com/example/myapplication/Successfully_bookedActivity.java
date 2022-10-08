package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Successfully_bookedActivity extends AppCompatActivity {
    Button view_booking_btn;
    LinearLayout lsucess, lfailed;
    Session session;
    String Type,PromoCode,TotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_booked);
        session = new Session(Successfully_bookedActivity.this);
        Type = getIntent().getStringExtra(Constant.TYPE);
        PromoCode = getIntent().getStringExtra(Constant.PROMO_CODE);
        TotalPrice = getIntent().getStringExtra(Constant.TOTAL_PRICE);

        view_booking_btn = findViewById(R.id.view_booking_btn);
        lsucess = findViewById(R.id.lsucess);
        lfailed = findViewById(R.id.lfailed);

        view_booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Successfully_bookedActivity.this, OrderListActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
       order();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Successfully_bookedActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void order()
    {

        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.PACKAGE_ID,Constant.PACKAGE_ID_VAL);
        params.put(Constant.VENUE_ID,session.getData(Constant.VENUE_ID));
        params.put(Constant.ADDRESS_ID,Constant.ADDRESS_ID_VAL);
        params.put(Constant.PRICE,TotalPrice);
        params.put(Constant.PROMO_CODE,PromoCode);
        params.put(Constant.EVENT_DATE,session.getData(Constant.EVENT_DATE));
        params.put(Constant.EVENT_TIME,session.getData(Constant.EVENT_TIME));
        params.put(Constant.PINCODE,session.getData(Constant.PINCODE));
        params.put(Constant.TYPE,Type);
        params.put(Constant.TIME_SLOT_ID,session.getData(Constant.TIME_SLOT_ID));
         ApiConfig.RequestToVolley((result, response) -> {
             Log.d("BOOKED_RES",response);
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        lsucess.setVisibility(View.VISIBLE);
                    }
                    else {
                        lfailed.setVisibility(View.VISIBLE);
                        Toast.makeText(Successfully_bookedActivity.this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    lfailed.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }



            }

            //pass url
        }, Successfully_bookedActivity.this, Constant.ORDERS, params,true);




    }
}