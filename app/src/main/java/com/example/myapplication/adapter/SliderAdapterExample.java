package com.example.myapplication.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.Package_listActivity;
import com.example.myapplication.PackagedetailsActivity;
import com.example.myapplication.R;
import com.example.myapplication.SliderItem;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Slide;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Activity context;
    private ArrayList<Slide> mSliderItems = new ArrayList<>();
    Session session;

    public SliderAdapterExample(Activity context) {
        this.context = context;
    }

    public void renewItems(ArrayList<Slide> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

//    public void addItem(A sliderItem) {
//        this.mSliderItems.add(sliderItem);
//        notifyDataSetChanged();
//    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        session = new Session(context);


        Slide sliderItem = mSliderItems.get(position);

        viewHolder.textViewDescription.setText(sliderItem.getName());
        viewHolder.textViewDescription.setTextSize(16);
        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImage())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sliderItem.getCategory_id() != null && !sliderItem.getCategory_id() .equals("")){
                    session.setData(Constant.CATEGORY_ID,sliderItem.getCategory_id());
                    Intent intent = new Intent(context, Package_listActivity.class);
                    intent.putExtra(Constant.CATEGORY_ID,sliderItem.getCategory_id());
                    context.startActivity(intent);

                }
                else if (sliderItem.getPackage_id() != null && !sliderItem.getPackage_id() .equals("")){
                    goToPackage(sliderItem.getPackage_id());

                }else if (sliderItem.getLink() != null && !sliderItem.getLink() .equals("")){
                    String url = sliderItem.getLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);

                }
            }
        });

    }

    private void goToPackage(String package_id)
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.PACKAGE_ID,package_id);
        params.put(Constant.PINCODE,session.getData(Constant.PINCODE));
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("PACK_RES",response);

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.CATEGORY_ID,jsonArray.getJSONObject(0).getString(Constant.CATEGORY_ID));
                        Intent intent = new Intent(context, PackagedetailsActivity.class);
                        intent.putExtra(Constant.IMAGE_PACKAGE,jsonArray.getJSONObject(0).getString(Constant.COVER_PHOTO));
                        intent.putExtra(Constant.IMAGE1,jsonArray.getJSONObject(0).getString(Constant.IMAGE1));
                        intent.putExtra(Constant.IMAGE2,jsonArray.getJSONObject(0).getString(Constant.IMAGE2));
                        intent.putExtra(Constant.IMAGE3,jsonArray.getJSONObject(0).getString(Constant.IMAGE3));
                        intent.putExtra(Constant.IMAGE4,jsonArray.getJSONObject(0).getString(Constant.IMAGE4));
                        intent.putExtra(Constant.PACKAGE_NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        intent.putExtra(Constant.PACKAGE_PRICE,jsonArray.getJSONObject(0).getString(Constant.PRICE));
                        intent.putExtra(Constant.PACKAGE_DESCRIPTION,jsonArray.getJSONObject(0).getString(Constant.DESCRIPION));
                        Constant.PACKAGE_ID_VAL = jsonArray.getJSONObject(0).getString(Constant.ID);
                        session.setData(Constant.PRICE,jsonArray.getJSONObject(0).getString(Constant.PRICE));
                        context.startActivity(intent);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }

        }, context, Constant.PACKAGE_BY_ID_URL, params, true);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }

}