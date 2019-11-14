package com.foodona.restaurent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.restaurent.Modal.Modal_Order;
import com.foodona.restaurent.R;

import java.util.ArrayList;

public class AdapterOffer extends RecyclerView.Adapter <AdapterOffer.ViewHolder> {
    Context context;
    ArrayList<Modal_Order> list;

    public AdapterOffer(Context context, ArrayList<Modal_Order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_offer, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Modal_Order modal_order = list.get(position);
        holder.netd.setText(modal_order.getOrder());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView netd;
        TextView dish_name;

        ImageView restimage;

        public ViewHolder(final View itemView) {
            super(itemView);
            /*context = itemView.getContext();*/

            netd = itemView.findViewById(R.id.netd);

        }
    }
}