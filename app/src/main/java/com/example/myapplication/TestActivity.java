package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.DateSlotAdapter;
import com.example.myapplication.model.DateSlot;
import com.example.myapplication.model.Slide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DateSlot> dateSlots = new ArrayList<DateSlot>();
    Activity activity;
    DateSlotAdapter dateSlotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        activity = new TestActivity();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));


        SimpleDateFormat sdf = new SimpleDateFormat("EE\ndd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
        for (int i = 0; i < 10; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i);
            String day = sdf.format(calendar.getTime());
            String day2 = sdf2.format(calendar.getTime());
            DateSlot dateSlot = new DateSlot((i+1)+"",day,day2);
            dateSlots.add(dateSlot);
        }
        dateSlotAdapter = new DateSlotAdapter(TestActivity.this, dateSlots);
        recyclerView.setAdapter(dateSlotAdapter);



    }
}