package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.adapter.PackagelistAdapter;
import com.example.myapplication.adapter.RecommendedAdapter;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Package;
import com.example.myapplication.model.Recommend;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Package_listActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Activity activity;
    PackagelistAdapter packagelistAdapter;
    String  categoryId;
    Session session;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);
        categoryId = getIntent().getStringExtra(Constant.CATEGORY_ID);



        recyclerView = findViewById(R.id.recyclerView);
        back_btn = findViewById(R.id.back_btn);
        activity = Package_listActivity.this;
        session = new Session(activity);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Packagelist();




    }

    private void Packagelist() {

        Map<String, String> params = new HashMap<>();
        params.put(Constant.PINCODE,session.getData(Constant.PINCODE));
        params.put(Constant.CATEGORY_ID,categoryId);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Package> packages = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Package group = g.fromJson(jsonObject1.toString(), Package.class);
                                packages.add(group);
                            } else {
                                break;
                            }
                        }

                        packagelistAdapter = new PackagelistAdapter(activity, packages);
                        recyclerView.setAdapter(packagelistAdapter);




                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.PACKAGE_LIST, params, true);


    }
}