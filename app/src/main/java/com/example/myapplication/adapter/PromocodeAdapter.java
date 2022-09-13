package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        if (promocode.getDiscount_type().equals("percentage")){
            holder.tvOffer.setText(promocode.getDiscount() +" %");

        }
        else {
            holder.tvOffer.setText("₹ "+promocode.getDiscount());


        }


        holder.tvPromocode.setText(promocode.getPromo_code());
        holder.tvValid.setText("Valid until "+promocode.getEnd_date());
        holder.imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Promocode Copied", Toast.LENGTH_SHORT).show();
                ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", promocode.getPromo_code());
                clipboard.setPrimaryClip(clip);

            }
        });





    }

    @Override
    public int getItemCount() {
        return promocodes.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {
        final TextView tvOffer,tvPromocode,tvValid;
        final ImageView imgCopy;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvOffer = itemView.findViewById(R.id.tvOffer);
            tvPromocode = itemView.findViewById(R.id.tvPromocode);
            tvValid = itemView.findViewById(R.id.tvValid);
            imgCopy = itemView.findViewById(R.id.imgCopy);



        }
    }
}

