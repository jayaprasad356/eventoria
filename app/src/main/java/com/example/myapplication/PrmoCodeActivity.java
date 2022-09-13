package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.fragment.GiftFragment;

public class PrmoCodeActivity extends AppCompatActivity {
    GiftFragment giftFragment;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prmo_code);
        back_btn = findViewById(R.id.back_btn);
        giftFragment = new GiftFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment,giftFragment).commit();
    }
}