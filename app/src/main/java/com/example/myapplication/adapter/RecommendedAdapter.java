package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Recommend;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecommendedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Recommend> recommends;

    public RecommendedAdapter(Activity activity, ArrayList<Recommend> recommends) {
        this.activity = activity;
        this.recommends = recommends ;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recommended_item, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Recommend recommend = recommends.get(position);

        Glide.with(activity).load(recommend.getCover_photo()).into(holder.imgRecommendedlist);
        holder.tvRecommendname.setText(recommend.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return recommends.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final ImageView imgRecommendedlist;
        final TextView tvRecommendname;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            imgRecommendedlist = itemView.findViewById(R.id.imgRecommendedlist);
            tvRecommendname = itemView.findViewById(R.id.tvRecommendname);


        }
    }
}

