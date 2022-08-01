package com.example.myapplication.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Package_listActivity;
import com.example.myapplication.PackagedetailsActivity;
import com.example.myapplication.R;
import com.example.myapplication.SliderItem;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.PackagelistAdapter;
import com.example.myapplication.adapter.RecommendedAdapter;
import com.example.myapplication.adapter.SliderAdapterExample;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Package;
import com.example.myapplication.model.Recommend;
import com.example.myapplication.model.Slide;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {

    SliderView sliderView;
    private SliderAdapterExample adapter;
    RecyclerView recyclerView,recyclerView1;
    Activity activity;

    public HomeFragment() {
        // Required empty public constructor
    }


    TextView viewall_bestcollection_tv,viewall_package;
    int[] images = {R.drawable.profile_img,
            R.drawable.profile_img};
    CategoryAdapter categoryAdapter;
    RecommendedAdapter recommendedAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        sliderView = root.findViewById(R.id.image_slider);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView1 = root.findViewById(R.id.recyclerView1);

        activity = getActivity();

        recyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));


        viewall_package = root.findViewById(R.id.viewall_package);
        viewall_bestcollection_tv = root.findViewById(R.id.viewall_bestcollection_tv);



        adapter = new SliderAdapterExample(getActivity());
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




        viewall_bestcollection_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Package_listActivity.class);
                startActivity(intent);
            }
        });
        viewall_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Package_listActivity.class);
                startActivity(intent);
            }
        });


        categoryList();
        recommendedList();








        return root;




    }

    private void recommendedList() {

        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("Rec_res",response);


            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Recommend> recommends = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Recommend group = g.fromJson(jsonObject1.toString(), Recommend.class);
                                recommends.add(group);
                            } else {
                                break;
                            }
                        }

                        recommendedAdapter = new RecommendedAdapter(activity, recommends);
                        recyclerView1.setAdapter(recommendedAdapter);




                    }
                    else {
                        Toast.makeText(getActivity(), ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.RECOMMENDED_LIST, params, true);

    }

    private void categoryList() {


        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("CAT_RES",response);

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Category>  categories = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Category group = g.fromJson(jsonObject1.toString(), Category.class);
                                categories.add(group);
                            } else {
                                break;
                            }
                        }

                        //important
                        categoryAdapter = new CategoryAdapter(activity, categories);
                        recyclerView.setAdapter(categoryAdapter);




                    }
                    else {
                        Toast.makeText(getActivity(), ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.CATEGORY_LIST, params, true);


    }

//    public void renewItems() {
//        List<SliderItem> sliderItemList = new ArrayList<>();
//        //dummy data
//        for (int i = 0; i < 5; i++) {
//            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Slider Item " + i);
//            if (i % 2 == 0) {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//            } else {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
//            }
//            sliderItemList.add(sliderItem);
//        }
//        adapter.renewItems(sliderItemList);
//    }
    private void slideslist() {

        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Slide> slides = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Slide group = g.fromJson(jsonObject1.toString(), Slide.class);
                                slides.add(group);
                            } else {
                                break;
                            }
                        }
                        adapter.renewItems(slides);



                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.SLIDES_LIST, params, true);


    }


}