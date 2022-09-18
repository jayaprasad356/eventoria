package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MysummaryActivity extends AppCompatActivity {
    ImageView ivProductimg;
    String getPackage_name,getPackage_img,getPackage_price,getAddress;
    TextView tvProductname,tvPrice,tvadresss,tvProductprice,tvTotalprice;
    Session session;
    Activity activity;
    Button btnConfirm;
    EditText etPromoCode;
    Button btnApply;
    String PromoCode = "";
    TextView viewPromo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysummary);
        activity = MysummaryActivity.this;
        session = new Session(activity);


        getPackage_img = session.getData(Constant.PACKAGE_IMAGE);
        getPackage_name = session.getData(Constant.PACKAGE_NAME);
        getPackage_price = session.getData(Constant.PACKAGE_PRICE);
        getAddress = getIntent().getStringExtra(Constant.ADDRESS);

        ivProductimg = findViewById(R.id.ivProductimg);
        tvProductname = findViewById(R.id.tvProductname);
        tvPrice = findViewById(R.id.tvPrice);
        tvTotalprice = findViewById(R.id.tvTotalPrice);
        tvadresss = findViewById(R.id.tvadresss);
        tvProductprice = findViewById(R.id.tvProductprice);
        btnConfirm = findViewById(R.id.btnConfirm);
        etPromoCode = findViewById(R.id.etPromoCode);
        btnApply = findViewById(R.id.btnApply);
        viewPromo = findViewById(R.id.viewPromo);


        Glide.with(activity).load(getPackage_img).into(ivProductimg);
        tvProductname.setText(getPackage_name);
        tvPrice.setText("₹"+getPackage_price);
        tvProductname.setText("₹"+getPackage_name);
        tvadresss.setText(getAddress);
        tvTotalprice.setText("₹"+getPackage_price);

        viewPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(activity,PrmoCodeActivity.class);
                startActivity(intent1);
                //showBottomSheetDialog();
            }
        });


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
                Intent intent = new Intent(MysummaryActivity.this,Successfully_bookedActivity.class);
                intent.putExtra(Constant.TYPE,"own");
                intent.putExtra(Constant.PROMO_CODE,PromoCode);
                intent.putExtra(Constant.TOTAL_PRICE,getPackage_price);
                startActivity(intent);
            }
        });

    }

    private void applyPromoCode()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.VALIDATE_PROMO_CODE,"1");
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.PROMO_CODE,etPromoCode.getText().toString().trim());
        params.put(Constant.TOTAL,getPackage_price);
        params.put(Constant.CATEGORY_ID,session.getData(Constant.CATEGORY_ID));
        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (!jsonObject.getBoolean(Constant.ERROR)) {
                        etPromoCode.setEnabled(false);
                        btnApply.setEnabled(false);
                        Toast.makeText(activity, "PromoCode Applied Succesfully", Toast.LENGTH_SHORT).show();
                        getPackage_price = jsonObject.getString(Constant.DISCOUNTED_AMOUNT);
                        tvTotalprice.setText("₹"+getPackage_price);
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