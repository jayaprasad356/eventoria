package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.VenueAdapter;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.Venue;
import com.google.android.material.chip.Chip;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Activity activity;
    OrderAdapter orderAdapter;
    Session session;
    Chip Myaddress,Venueaddress;
    String type = "own";
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_list);
        recyclerView = findViewById(R.id.recyclerView);
        Myaddress = findViewById(R.id.Myaddress);
        Venueaddress = findViewById(R.id.Venueaddress);
        back_btn = findViewById(R.id.back_btn);
        activity = OrderListActivity.this;
        session = new Session(activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        Myaddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    type = "own";
                    orderList();
                }
            }
        });
        Venueaddress.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                type = "venue";
                orderList();
            }
        });
        back_btn.setOnClickListener(view -> onBackPressed());
        orderList();




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OrderListActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void orderList()
    {

        Map<String, String> params = new HashMap<>();
        params.put(Constant.TYPE,type);
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Order> venues = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Order group = g.fromJson(jsonObject1.toString(), Order.class);
                                venues.add(group);
                            } else {
                                break;
                            }
                        }

                        orderAdapter = new OrderAdapter(activity, venues);
                        recyclerView.setAdapter(orderAdapter);




                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.ORDER_LIST, params, true);


    }
}