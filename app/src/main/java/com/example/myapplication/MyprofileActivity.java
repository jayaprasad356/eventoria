package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.PackagelistAdapter;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Package;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyprofileActivity extends AppCompatActivity {
    EditText edPincode,edNameprofile;
    TextView tvMobileno;
    Button btnNext;
    Session session;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);


        activity = MyprofileActivity.this;
        session = new Session(MyprofileActivity.this);



        edPincode = findViewById(R.id.edPincode);
        btnNext = findViewById(R.id.btnNext);
        edNameprofile = findViewById(R.id.edNameprofile);
        tvMobileno = findViewById(R.id.tvMobileno);
        tvMobileno.setText(session.getData(Constant.MOBILE));
        edNameprofile.setText(session.getData(Constant.NAME));
        edPincode.setText(session.getData(Constant.PINCODE));



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateProfile();

            }
        });







    }

    private void updateProfile() {


        Map<String, String> params = new HashMap<>();
        params.put(Constant.PINCODE,edPincode.getText().toString().trim());
        params.put(Constant.NAME,edNameprofile.getText().toString().trim());
        params.put(Constant.MOBILE,tvMobileno.getText().toString().trim());
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("Rec_res",response);

            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.PINCODE,jsonArray.getJSONObject(0).getString(Constant.PINCODE));
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        Intent intent = new Intent(activity,MainActivity.class);
                        startActivity(intent);
                        finish();


                        Toast.makeText(activity, "profile Update succesfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();

                }



            }
            else {
                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        },activity, Constant.UPDATE_PROFILE, params,true);



    }
}