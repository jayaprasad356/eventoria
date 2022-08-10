package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Package_listActivity;
import com.example.myapplication.R;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Order;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Order> orders;

    public OrderAdapter(Activity activity, ArrayList<Order> orders) {
        this.activity = activity;
        this.orders = orders;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.order_items, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Order order = orders.get(position);

        holder.tvOrderId.setText("Booking Id : "+order.getId());
        holder.tvAddress.setText("Address : "+order.getAddress());
        holder.tvPackageName.setText("Package Name : "+order.getPackage_name());
        holder.tvPackagePrice.setText("Rs."+order.getPrice());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {


        final TextView tvOrderId,tvAddress,tvPackageName,tvPackagePrice;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPackageName = itemView.findViewById(R.id.tvPackageName);
            tvPackagePrice = itemView.findViewById(R.id.tvPackagePrice);


        }
    }
}

