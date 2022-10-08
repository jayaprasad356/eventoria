package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangeAddressActivity extends AppCompatActivity {

    Button btnAddress;
    EditText etName,etAddress,etPincode,etState;
    Session session;
    Spinner spinPlace,spinDistrict;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        activity = ChangeAddressActivity.this;
        session = new Session(ChangeAddressActivity.this);

        btnAddress = findViewById(R.id.btnAddress);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        spinDistrict = findViewById(R.id.spinDistrict);
        etPincode = findViewById(R.id.etPincode);
        etState = findViewById(R.id.etState);
        spinPlace = findViewById(R.id.spinPlace);
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
                else if (etPincode.getText().toString().trim().equals("")){
                    etPincode.setError("empty");
                    etPincode.requestFocus();
                }
                else if (spinDistrict.getSelectedItemId() == 0){
                    Toast.makeText(activity, "Select District", Toast.LENGTH_SHORT).show();
                }
                else if (spinPlace.getSelectedItemId() == 0){
                    Toast.makeText(activity, "Select Place", Toast.LENGTH_SHORT).show();
                }
                else if (etState.getText().toString().trim().equals("")){
                    etState.setError("empty");
                    etState.requestFocus();
                }
                else {

                    Map<String, String> params = new HashMap<>();
                    params.put(Constant.NAME,etName.getText().toString().trim());
                    params.put(Constant.ADDRESS,etAddress.getText().toString().trim());
                    params.put(Constant.DISTRICT,spinDistrict.getSelectedItem().toString().trim());
                    params.put(Constant.PLACE,spinPlace.getSelectedItem().toString().trim());
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
                                            spinPlace.getSelectedItem().toString().trim()+",\n"+
                                            spinDistrict.getSelectedItem().toString().trim()+",\n"+etState.getText().toString()+",\n"+etPincode.getText().toString().trim());
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
        defaultspinnerList();
        etPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 6){
                    setSpinnerList();
                }
                else {
                    defaultspinnerList();

                }

            }
        });

    }
    private void defaultspinnerList() {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("Select Place");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPlace.setAdapter(adapter);
        spinPlace.setSelection(0);

        ArrayList<String> arr2 = new ArrayList<String>();
        arr2.add("Select District");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDistrict.setAdapter(adapter2);
        spinDistrict.setSelection(0);
    }
    private void setSpinnerList()
    {
        if (etPincode.getText().length() == 6){
            String pincodeurl = "http://www.postalpincode.in/api/pincode/"+etPincode.getText().toString().trim();
            Map<String, String> params = new HashMap<>();
            ApiConfig.GetVolleyRequest((result, response) -> {
                if (result) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getString("Status").equals("Success")) {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("PostOffice");
                            Gson g = new Gson();
                            ArrayList<String> villagename = new ArrayList<String>();
                            ArrayList<String> district = new ArrayList<String>();
                            villagename.add("Select Village Name");
                            district.add("Select District");
                            etState.setText(jsonArray.getJSONObject(0).getString("State"));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                String name_ = jsonArray.getJSONObject(i).getString("Name");
                                String district_ = jsonArray.getJSONObject(i).getString("District");



                                if (!district.contains(district_)) {

                                    district.add(district_);

                                }
                                if (!villagename.contains(name_)) {

                                    villagename.add(name_);

                                }
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, villagename);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinPlace.setAdapter(adapter);
                            spinPlace.setSelection(0);
                            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, district);
                            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinDistrict.setAdapter(adapter2);
                            spinDistrict.setSelection(0);


                        }
                        else {
                            Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, activity, pincodeurl, params,true);


        }


    }
}