package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.adapter.NotificationAdapter;
import com.example.myapplication.adapter.PromocodeAdapter;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Notification;
import com.example.myapplication.model.Promocode;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CouponActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Activity activity;
    PromocodeAdapter promocodeAdapter;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        activity = CouponActivity.this;
        session = new Session(activity);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));



        mypromocode();

    }

    private void mypromocode() {


        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("CODE_RES", response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Promocode> promocodes = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Promocode group = g.fromJson(jsonObject1.toString(), Promocode.class);
                                promocodes.add(group);
                            } else {
                                break;
                            }
                        }
                        promocodeAdapter = new PromocodeAdapter(activity, promocodes);
                        recyclerView.setAdapter(promocodeAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.MYPROMOCODE, params, true);


    }
}