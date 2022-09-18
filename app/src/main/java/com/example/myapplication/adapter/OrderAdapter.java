package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ChangeAddressActivity;
import com.example.myapplication.MysummaryActivity;
import com.example.myapplication.OrderListActivity;
import com.example.myapplication.Package_listActivity;
import com.example.myapplication.R;
import com.example.myapplication.helper.ApiConfig;
import com.example.myapplication.helper.Constant;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        if (!order.getStatus().equals("2") && order.getCancel_order().equals("true")){
            holder.btnCancel.setVisibility(View.VISIBLE);
            holder.btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancelOrder(order.getId());
                }
            });
        }


    }

    private void cancelOrder(String id)
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.ORDER_ID,id);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Toast.makeText(activity, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        ((OrderListActivity)activity).orderList();
                    }
                    else {
                        Toast.makeText(activity, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }

            //pass url
        }, activity, Constant.CANCEL_ORDER_URL, params,true);



    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {


        final TextView tvOrderId,tvAddress,tvPackageName,tvPackagePrice;
        Button btnCancel;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPackageName = itemView.findViewById(R.id.tvPackageName);
            tvPackagePrice = itemView.findViewById(R.id.tvPackagePrice);
            btnCancel = itemView.findViewById(R.id.btnCancel);


        }
    }
}

