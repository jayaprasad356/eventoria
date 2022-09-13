package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.DateSlotAdapter;
import com.example.myapplication.adapter.SliderAdapterExample;
import com.example.myapplication.adapter.TimeSlotsAdapter;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.DateSlot;
import com.example.myapplication.model.Slide;
import com.example.myapplication.model.TimeSlots;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class VenuedetailsActivity extends AppCompatActivity {


    TextView tvVenuename,tvAddress,tvDescription,tvChooseDate;
    String Venuename,VenueAddress,Venueimg,image1,image2,image3,image4,description;;
    Activity activity;
    TimeSlotsAdapter timeSlotsAdapter;
    RecyclerView recyclerView,daterecyclerView;
    ArrayList<TimeSlots> timeSlots = new ArrayList<>();
    SliderView sliderView;
    private SliderAdapterExample adapter;
    Button btnConfirm;
    public static String slotday = "";
    Session session;
    DateSlotAdapter dateSlotAdapter;
    ArrayList<DateSlot> dateSlots = new ArrayList<DateSlot>();
    ArrayList<TimeSlots> selectedtimeSlots = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venuedetails);
        activity = VenuedetailsActivity.this;
        session = new Session(activity);
        sliderView = findViewById(R.id.image_slider);
        tvDescription = findViewById(R.id.tvDescription);
        tvChooseDate = findViewById(R.id.tvChooseDate);
        Venuename = getIntent().getStringExtra(Constant.VENUE_NAME);
        VenueAddress = getIntent().getStringExtra(Constant.VENUE_ADDRESS);
        Venueimg = getIntent().getStringExtra(Constant.VENUE_IMG);
        description = getIntent().getStringExtra(Constant.DESCRIPION);
        recyclerView = findViewById(R.id.recyclerView);
        daterecyclerView = findViewById(R.id.daterecyclerView);
        tvAddress = findViewById(R.id.tvAddress);
        tvVenuename = findViewById(R.id.tvVenuename);
        btnConfirm = findViewById(R.id.btnConfirm);
        daterecyclerView = findViewById(R.id.daterecyclerView);
        image1 = getIntent().getStringExtra(Constant.IMAGE1);
        image2 = getIntent().getStringExtra(Constant.IMAGE2);
        image3 = getIntent().getStringExtra(Constant.IMAGE3);
        image4 = getIntent().getStringExtra(Constant.IMAGE4);
        adapter = new SliderAdapterExample(VenuedetailsActivity.this);
        slideslist();
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });

        tvVenuename.setText(Venuename);
        tvAddress.setText(VenueAddress);
        tvDescription.setText(description);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        daterecyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        timeSlots = (ArrayList<TimeSlots>) args.getSerializable("ARRAYLIST");
        timeslotList();



        SimpleDateFormat sdf = new SimpleDateFormat("EE\ndd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i);
            String day = sdf.format(calendar.getTime());
            String day2 = sdf2.format(calendar.getTime());
            DateSlot dateSlot = new DateSlot((i+1)+"",day,day2);
            dateSlots.add(dateSlot);
        }
        dateSlotAdapter = new DateSlotAdapter(activity, dateSlots);
        daterecyclerView.setAdapter(dateSlotAdapter);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> timeSlotsId = new ArrayList<String>();
                int totalprice = Integer.parseInt(session.getData(Constant.PACKAGE_PRICE)) ;
                selectedtimeSlots.clear();
                for (TimeSlots model : timeSlots) {
                    if (model.isSelected()) {
                        timeSlotsId.add(model.getId());
                        totalprice += Integer.parseInt(model.getPrices());
                        selectedtimeSlots.add(model);

                    }
                }
                if (slotday.equals("")){
                    Toast.makeText(activity, "Select Date", Toast.LENGTH_SHORT).show();

                }
                else if (timeSlotsId.size() == 0){
                    Toast.makeText(activity, "Select Time Slot", Toast.LENGTH_SHORT).show();
                }
                else{
                    session.setData(Constant.EVENT_DATE,slotday);

                    session.setData(Constant.TIME_SLOT_ID,timeSlotsId.toString());
                    session.setData(Constant.PRICE,""+totalprice);
                    Intent intent = new Intent(activity, VenueSummaryActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST",(Serializable)selectedtimeSlots);
                    intent.putExtra("BUNDLE",args);
                    intent.putExtra(Constant.TYPE,"venue");
                    intent.putExtra(Constant.ADDRESS,VenueAddress);
                    activity.startActivity(intent);

                }

                Log.d("TIME_SLOT_ID",timeSlotsId.toString());
            }
        });
        tvChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });


    }

    private void showDatePicker()
    {
        int mYear,mMonth,mDay;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        slotday = year + "-"+convertTwodigit(monthOfYear)+"-"+ convertTwodigit(dayOfMonth);
                        tvChooseDate.setText(slotday);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private String convertTwodigit(int s)
    {
        long val = (long) s;
        String format = "%1$02d";
        return (String.format(format,val));
    }

    private void timeslotList()
    {

        timeSlotsAdapter = new TimeSlotsAdapter(activity, timeSlots,"venue");
        recyclerView.setAdapter(timeSlotsAdapter);
    }
    private void slideslist() {
        ArrayList<Slide> slides = new ArrayList<>();
        Slide coverimg = new Slide("1","",Venueimg,"");
        Slide slide1 = new Slide("1","",image1,"");
        Slide slide2 = new Slide("1","",image2,"");
        Slide slide3 = new Slide("1","",image3,"");
        Slide slide4 = new Slide("1","",image4,"");
        if (!Venueimg.equals("")){
            slides.add(coverimg);

        }
        if (!image1.equals("")){
            slides.add(slide1);

        }
        if (!image2.equals("")){
            slides.add(slide2);

        }
        if (!image3.equals("")){
            slides.add(slide3);

        }
        if (!image4.equals("")){
            slides.add(slide4);
        }

        adapter.renewItems(slides);

    }
}