package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.adapter.PromocodeAdapter;
import com.example.myapplication.adapter.TimeSlotsAdapter;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Promocode;
import com.example.myapplication.model.TimeSlots;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VenueSummaryActivity extends AppCompatActivity {
    ImageView ivProductimg;
    String getPackage_name,getPackage_img,getPackage_price,getAddress,TotalPrice;
    TextView tvProductname,tvPrice,tvadresss,tvProductprice,tvTotalprice;
    Session session;
    Activity activity;
    Button btnConfirm;
    EditText etPromoCode;
    Button btnApply;
    ArrayList<TimeSlots> timeSlots = new ArrayList<>();
    RecyclerView recyclerView,promorecyclerView;
    TimeSlotsAdapter timeSlotsAdapter;
    String PromoCode = "";
    PromocodeAdapter promocodeAdapter;
    TextView viewPromo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_summary);
        activity = VenueSummaryActivity.this;
        session = new Session(activity);


        getPackage_img = session.getData(Constant.PACKAGE_IMAGE);
        getPackage_name = session.getData(Constant.PACKAGE_NAME);
        getPackage_price = session.getData(Constant.PACKAGE_PRICE);
        getAddress = getIntent().getStringExtra(Constant.ADDRESS);

        ivProductimg = findViewById(R.id.ivProductimg);
        recyclerView = findViewById(R.id.recyclerView);
        promorecyclerView = findViewById(R.id.promorecyclerView);
        tvProductname = findViewById(R.id.tvProductname);
        tvPrice = findViewById(R.id.tvPrice);
        tvTotalprice = findViewById(R.id.tvTotalPrice);
        tvadresss = findViewById(R.id.tvadresss);
        tvProductprice = findViewById(R.id.tvProductprice);
        btnConfirm = findViewById(R.id.btnConfirm);
        etPromoCode = findViewById(R.id.etPromoCode);
        btnApply = findViewById(R.id.btnApply);
        viewPromo = findViewById(R.id.viewPromo);
        TotalPrice = session.getData(Constant.PRICE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
        recyclerView.setLayoutManager(gridLayoutManager);


        Glide.with(activity).load(getPackage_img).into(ivProductimg);
        tvProductname.setText(getPackage_name);
        tvPrice.setText("₹"+getPackage_price);
        tvadresss.setText(getAddress);
        tvTotalprice.setText("₹"+TotalPrice);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        timeSlots = (ArrayList<TimeSlots>) args.getSerializable("ARRAYLIST");
        timeslotList();

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etPromoCode.getText().toString().trim().equals("")){
                    applyPromoCode();
                }
            }
        });


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,Successfully_bookedActivity.class);
                intent.putExtra(Constant.TYPE,"venue");
                intent.putExtra(Constant.PROMO_CODE,PromoCode);
                intent.putExtra(Constant.TOTAL_PRICE,TotalPrice);
                startActivity(intent);
            }
        });

        viewPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(activity,PrmoCodeActivity.class);
                startActivity(intent1);
                //showBottomSheetDialog();
            }
        });

    }
    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.promo_code_sheet);

        RecyclerView promorecyclerView = bottomSheetDialog.findViewById(R.id.promorecyclerView);
        promorecyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));



        mypromocode();

        bottomSheetDialog.show();
    }
    private void mypromocode() {


        Map<String, String> params = new HashMap<>();
        params.put(Constant.GET_PROMO_CODES,"1");
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.getBoolean(Constant.ERROR)) {
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
                        promorecyclerView.setAdapter(promocodeAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.VALIDATE_PROMO_CODE_URL, params, true);


    }
    private void timeslotList()
    {

        timeSlotsAdapter = new TimeSlotsAdapter(activity, timeSlots, "summary");
        recyclerView.setAdapter(timeSlotsAdapter);
    }
    private void applyPromoCode()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.VALIDATE_PROMO_CODE,"1");
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.PROMO_CODE,etPromoCode.getText().toString().trim());
        params.put(Constant.CATEGORY_ID,session.getData(Constant.CATEGORY_ID));
        params.put(Constant.TOTAL,getPackage_price);
        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (!jsonObject.getBoolean(Constant.ERROR)) {
                        etPromoCode.setEnabled(false);
                        btnApply.setEnabled(false);
                        Toast.makeText(activity, "PromoCode Applied Succesfully", Toast.LENGTH_SHORT).show();
                        TotalPrice = jsonObject.getString(Constant.DISCOUNTED_AMOUNT);
                        tvTotalprice.setText("₹"+TotalPrice);
                        PromoCode = etPromoCode.getText().toString().trim();

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
        },activity, Constant.VALIDATE_PROMO_CODE_URL, params,true);


    }
}