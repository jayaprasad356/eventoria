package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ChangeAddressActivity extends AppCompatActivity {

    Button btnAddress;
    EditText etName,etAddress,etDistrict,etPincode,etState;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        session = new Session(ChangeAddressActivity.this);

        btnAddress = findViewById(R.id.btnAddress);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etDistrict = findViewById(R.id.etDistrict);
        etPincode = findViewById(R.id.etPincode);
        etState = findViewById(R.id.etState);
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().trim().equals("")){
                    etName.setError("empty");
                    etName.requestFocus();
                }
                else if (etAddress.getText().toString().trim().equals("")){
                    etAddress.setError("empty");
                    etAddress.requestFocus();
                }
                else if (etDistrict.getText().toString().trim().equals("")){
                    etDistrict.setError("empty");
                    etDistrict.requestFocus();
                }
                else if (etPincode.getText().toString().trim().equals("")){
                    etPincode.setError("empty");
                    etPincode.requestFocus();
                }
                else if (etState.getText().toString().trim().equals("")){
                    etState.setError("empty");
                    etState.requestFocus();
                }
                else {

                    Map<String, String> params = new HashMap<>();
                    params.put(Constant.NAME,etName.getText().toString().trim());
                    params.put(Constant.ADDRESS,etAddress.getText().toString().trim());
                    params.put(Constant.DISTRICT,etDistrict.getText().toString().trim());
                    params.put(Constant.PINCODE,etPincode.getText().toString().trim());
                    params.put(Constant.STATE,etState.getText().toString().trim());


                    ApiConfig.RequestToVolley((result, response) -> {
                        if (result) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);


                                if (jsonObject.getBoolean(Constant.SUCCESS)) {
                                    JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                                    Intent intent = new Intent(ChangeAddressActivity.this, MysummaryActivity.class);
                                    Constant.ADDRESS_ID_VAL = jsonArray.getJSONObject(0).getString(Constant.ID);
                                    intent.putExtra(Constant.TYPE,"own");
                                    intent.putExtra(Constant.ADDRESS,etName.getText().toString().trim()+",\n"+etAddress.getText().toString().trim()+",\n"+
                                            etDistrict.getText().toString().trim()+",\n"+etState.getText().toString()+",\n"+etPincode.getText().toString().trim());
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(ChangeAddressActivity.this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e){
                                e.printStackTrace();
                            }



                        }

                        //pass url
                    }, ChangeAddressActivity.this, Constant.ADDADDRESS, params,true);




                }


            }
        });

    }
}