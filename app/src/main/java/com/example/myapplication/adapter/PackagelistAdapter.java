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
import com.example.myapplication.model.Package;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PackagelistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Package> packages;
    Session session;

    public PackagelistAdapter(Activity activity, ArrayList<Package> packages) {
        this.activity = activity;
        this.packages = packages;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.package_item, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Package aPackage = packages.get(position);

        Glide.with(activity).load(aPackage.getCover_photo()).into(holder.imgPackage);
        holder.tvPack_name.setText(aPackage.getName());
        holder.tvPrice.setText("Rs. "+aPackage.getPrice());
        holder.tvDescription.setText(aPackage.getDescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, PackagedetailsActivity.class);
                intent.putExtra(Constant.IMAGE_PACKAGE,aPackage.getCover_photo());
                intent.putExtra(Constant.PACKAGE_NAME,aPackage.getName());
                intent.putExtra(Constant.PACKAGE_PRICE,aPackage.getPrice());
                intent.putExtra(Constant.PACKAGE_DESCRIPTION,aPackage.getDescription());
                Constant.PACKAGE_ID_VAL = aPackage.getId();
                session.setData(Constant.PRICE,aPackage.getPrice());
                        activity.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final ImageView imgPackage;
        final TextView tvPack_name,tvPrice,tvDescription;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            imgPackage = itemView.findViewById(R.id.imgPackage);
            tvPack_name = itemView.findViewById(R.id.tvPack_name);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDescription = itemView.findViewById(R.id.tvDescription);


        }
    }
}

