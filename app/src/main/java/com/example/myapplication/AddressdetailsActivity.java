package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddressdetailsActivity extends AppCompatActivity {

    Button proceed_btn;
    TextView tvChangeaddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressdetails);

        proceed_btn= findViewById(R.id.proceed_btn);
        tvChangeaddress= findViewById(R.id.tvChangeaddress);

        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressdetailsActivity.this,Successfully_bookedActivity.class);
                startActivity(intent);
            }
        });
        tvChangeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressdetailsActivity.this,ChangeAddressActivity.class);
                startActivity(intent);
            }
        });

    }
}