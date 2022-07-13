package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.helper.Constant;

public class LoginActivity extends AppCompatActivity {

    Button btnVerify;
    EditText etMobile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnVerify = findViewById(R.id.btnVerify);
        etMobile = findViewById(R.id.etMobile);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etMobile.length()==10){

                    Intent intent = new Intent(LoginActivity.this,OtpActivity.class);
                    intent.putExtra(Constant.MOBILE,etMobile.getText().toString().trim());
                    startActivity(intent);
                    finish();

                }

                else if(etMobile.length()==0){

                    Toast.makeText(LoginActivity.this, Constant.MOBILE_NUM_EMPTY, Toast.LENGTH_SHORT).show();


                }

                else {
                    Toast.makeText(LoginActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}