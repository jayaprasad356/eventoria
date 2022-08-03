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
import com.example.myapplication.Package_listActivity;
import com.example.myapplication.R;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.model.Category;


import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Category> categories;
    final int layout;
    final int totalsize;


    public CategoryAdapter(Activity activity, ArrayList<Category> categories, int layout, int totalsize) {
        this.activity = activity;
        this.categories = categories;
        this.layout = layout;
        this.totalsize = totalsize;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Category category = categories.get(position);

        Glide.with(activity).load(category.getImage()).into(holder.imgCategory);
        holder.tvCategoryname.setText(category.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Package_listActivity.class);
                intent.putExtra(Constant.CATEGORY_ID,category.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return totalsize;
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final ImageView imgCategory;
        final TextView tvCategoryname;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            tvCategoryname = itemView.findViewById(R.id.tvCategoryname);


        }
    }
}

