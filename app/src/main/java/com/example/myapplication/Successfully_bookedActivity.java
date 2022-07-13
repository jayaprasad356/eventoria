package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    RelativeLayout success_rl;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_booked);
        session = new Session(Successfully_bookedActivity.this);

        view_booking_btn = findViewById(R.id.view_booking_btn);
        success_rl = findViewById(R.id.success_rl);
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
        params.put(Constant.ADDRESS_ID,Constant.ADDRESS_ID_VAL);
        params.put(Constant.PRICE,Constant.PACKAGE_PRICE_VAL);
        params.put(Constant.TYPE,Constant.TYPE_VAL);
         ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        success_rl.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(Successfully_bookedActivity.this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }

            //pass url
        }, Successfully_bookedActivity.this, Constant.ORDERS, params,true);




    }
}