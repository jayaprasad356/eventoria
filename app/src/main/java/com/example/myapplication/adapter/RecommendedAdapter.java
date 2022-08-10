package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.PackagedetailsActivity;
import com.example.myapplication.R;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.helper.Session;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Recommend;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecommendedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Recommend> recommends;
    Session session;

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
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Recommend recommend = recommends.get(position);

        Glide.with(activity).load(recommend.getCover_photo()).into(holder.imgRecommendedlist);
        holder.tvRecommendname.setText(recommend.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PackagedetailsActivity.class);
                intent.putExtra(Constant.IMAGE_PACKAGE,recommend.getCover_photo());
                intent.putExtra(Constant.IMAGE1,recommend.getImage1());
                intent.putExtra(Constant.IMAGE2,recommend.getImage2());
                intent.putExtra(Constant.IMAGE3,recommend.getImage3());
                intent.putExtra(Constant.IMAGE4,recommend.getImage4());
                intent.putExtra(Constant.PACKAGE_NAME,recommend.getName());
                intent.putExtra(Constant.PACKAGE_PRICE,recommend.getPrice());
                intent.putExtra(Constant.PACKAGE_DESCRIPTION,recommend.getDescription());
                Constant.PACKAGE_ID_VAL = recommend.getId();
                session.setData(Constant.PRICE,recommend.getPrice());
                activity.startActivity(intent);
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

