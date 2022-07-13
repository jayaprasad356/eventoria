package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    Button next;
    EditText pincode_ed,name_ed;
    Session session;
    String MobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        session = new Session(ProfileActivity.this);

        MobileNumber = getIntent().getStringExtra(Constant.MOBILE);



        next = findViewById(R.id.next);
        pincode_ed = findViewById(R.id.pincode_ed);
        name_ed = findViewById(R.id.name_ed);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name_ed.length()>= 4 && pincode_ed.length()== 6){

                    Signin();

//                    Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
//                    startActivity(intent);
                }
                else if (name_ed.length()==0){
                    Toast.makeText(ProfileActivity.this, "Name is empty", Toast.LENGTH_SHORT).show();

                }

                else if(pincode_ed.length()==0){
                    Toast.makeText(ProfileActivity.this, "Pincode is empty", Toast.LENGTH_SHORT).show();
                }

                else if (name_ed.length()<=4){
                    Toast.makeText(ProfileActivity.this, "Name must be 4 latter", Toast.LENGTH_SHORT).show();
                }

                else if(pincode_ed.length()<=6){
                    Toast.makeText(ProfileActivity.this, "pincode must be 6 letter", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }

    private void Signin() {

        Map<String, String> params = new HashMap<>();
        params.put(Constant.MOBILE,MobileNumber);
        params.put(Constant.NAME,name_ed.getText().toString().trim());
        params.put(Constant.PINCODE,pincode_ed.getText().toString().trim());


        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setBoolean("is_logged_in", true);
                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.PINCODE,jsonArray.getJSONObject(0).getString(Constant.PINCODE));






                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        finish();
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
        }, ProfileActivity.this, Constant.SIGN_UP, params,true);



    }
}