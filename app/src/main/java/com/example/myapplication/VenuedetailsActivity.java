package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VenuedetailsActivity extends AppCompatActivity {

    Button proceed_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venuedetails);


        proceed_btn = findViewById(R.id.proceed_btn);
        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VenuedetailsActivity.this,Successfully_bookedActivity.class);
                startActivity(intent);
            }
        });
    }
}