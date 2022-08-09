package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Notification;
import com.example.myapplication.model.Promocode;

import java.util.ArrayList;


public class PromocodeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Promocode> promocodes;

    public PromocodeAdapter(Activity activity, ArrayList<Promocode> promocodes) {
        this.activity = activity;
        this.promocodes = promocodes;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.promo_code, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Promocode promocode = promocodes.get(position);

        holder.title.setText(promocode.getPromo_code());
        holder.description.setText(promocode.getMessage());
        holder.discount.setText(promocode.getDiscount());




    }

    @Override
    public int getItemCount() {
        return promocodes.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {
        final TextView title,description,discount;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            discount = itemView.findViewById(R.id.discount);



        }
    }
}

