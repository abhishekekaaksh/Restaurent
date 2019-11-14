package com.foodona.restaurent.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.restaurent.NotofiationModel.OrderBean;
import com.foodona.restaurent.R;

import java.util.List;

public class AdapterShowAcceptOrder extends RecyclerView.Adapter<AdapterShowAcceptOrder.ViewHolder> {
    Context context;
    List<OrderBean> list;

    public AdapterShowAcceptOrder(Context context, List<OrderBean> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public AdapterShowAcceptOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_show_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShowAcceptOrder.ViewHolder holder, int position) {
        OrderBean modal_order = list.get(position);
        Log.e("ghghh", list.size() + "");
        holder.tv_name.setText(  modal_order.getMenu_name());
        holder.tv_quantity.setText(modal_order.getItemQty());

    }

    @Override
    public int getItemCount() {
        Log.e("size","");
        return list.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_quantity;

        //ImageView restimage;

        public ViewHolder(final View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);


        }
    }
}
