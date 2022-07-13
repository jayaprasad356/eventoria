package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.helper.Constant;

public class CheckoutActivity extends AppCompatActivity {
    Button proceed_btn ;
    ImageView back_btn;
    RadioButton rbOwnplace,rbBookaplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);



        proceed_btn = findViewById(R.id.proceed_btn);
        back_btn = findViewById(R.id.back_btn);
        rbOwnplace = findViewById(R.id.rbOwnplace);
        rbBookaplace = findViewById(R.id.rbBookaplace);

        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rbOwnplace.isChecked()){

                    Intent intent = new Intent(CheckoutActivity.this,ChangeAddressActivity.class);
                    startActivity(intent);

                }
                else if(rbBookaplace.isChecked()){

//                    Intent intent = new Intent(CheckoutActivity.this,Venue_listActivity.class);
//                    startActivity(intent);

                }
                else {
                    Toast.makeText(CheckoutActivity.this, "Select Method", Toast.LENGTH_SHORT).show();
                }


            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}