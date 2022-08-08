package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.AboutusActivity;
import com.example.myapplication.Contact_usActivity;
import com.example.myapplication.CouponActivity;
import com.example.myapplication.FaqsActivity;
import com.example.myapplication.MyprofileActivity;
import com.example.myapplication.NotificationActivity;
import com.example.myapplication.OrderListActivity;
import com.example.myapplication.R;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;

public class ProfileFragment extends Fragment {

    TextView myorder_tv,contact_us_tv,notification_tv,tvMyprofile,tvName,tvMobileno,tvFaqs,tvAboutus,tvCoupons;
    Button signOut;
    Session session ;




    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        myorder_tv = root.findViewById(R.id.myorder_tv);
        tvMyprofile = root.findViewById(R.id.tvMyprofile);
        contact_us_tv = root.findViewById(R.id.contact_us_tv);
        notification_tv = root.findViewById(R.id.notification_tv);
        signOut = root.findViewById(R.id.signOut);
        tvName = root.findViewById(R.id.tvName);
        tvMobileno = root.findViewById(R.id.tvMobileno);
        tvFaqs = root.findViewById(R.id.tvFaqs);
        tvCoupons = root.findViewById(R.id.tvCoupons);
        tvAboutus = root.findViewById(R.id.tvAboutus);
        session = new Session(getActivity());


        myorder_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                startActivity(intent);
            }
        });
        tvFaqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FaqsActivity.class);
                startActivity(intent);
            }
        });
        tvCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CouponActivity.class);
                startActivity(intent);
            }
        });
        tvAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutusActivity.class);
                startActivity(intent);
            }
        });
        contact_us_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_usActivity.class);
                startActivity(intent);
            }
        });
        notification_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        tvMyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyprofileActivity.class);
                startActivity(intent);
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.logoutUser(getActivity());
            }
        });


        tvName.setText(session.getData(Constant.NAME));
        tvMobileno.setText(session.getData(Constant.MOBILE));



        return root;




    }
}