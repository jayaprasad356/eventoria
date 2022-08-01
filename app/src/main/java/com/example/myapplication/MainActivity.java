package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.fragment.GiftFragment;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    GiftFragment giftFragment;
    ProfileFragment profileFragment;
    HomeFragment homeFragment;
    FloatingActionButton homefloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        homefloat = findViewById(R.id.homefloat);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(this);
        giftFragment = new GiftFragment();
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        homefloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment,homeFragment).commit();


            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment,homeFragment).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.gift:
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment,giftFragment).commit();
                return true;



            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment,profileFragment).commit();
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment,homeFragment).commit();
                return true;

        }



        return false;
    }
}