package com.foodona.restaurent.Adapter;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.foodona.restaurent.Map.apsm;
import com.foodona.restaurent.Modal.OrderResponse;
import com.foodona.restaurent.NotofiationModel.OrderBean;
import com.foodona.restaurent.R;
import com.foodona.restaurent.Response.Modal_Order_Response;

import java.util.ArrayList;
import java.util.List;


public class AdapterReceOrder extends RecyclerView.Adapter<AdapterReceOrder.ViewHolder> {
    Context context;
    //ArrayList<OrderResponse> list;
    List<OrderBean> list;


    public AdapterReceOrder(Context context, List<OrderBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AdapterReceOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_rec_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReceOrder.ViewHolder holder, int position) {
        //OrderResponse modal_order = list.get(position);
        OrderBean modal_order = list.get(position);

        Log.e("ghghh", list.size() + "");
        holder.dishtime.setText(modal_order.getMenu_name()+"("+modal_order.getItemQty()+")");
        holder.amount.setText(""+modal_order.getItemAmt());
  //      Log.e("itemmenuname", modal_order.getMenu_name());
//        Log.e("itemamt", modal_order.getItemAmt());

        //holder.order.setText("Order Number :" + modal_order.getOrder_id());
        holder.tdrivewr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, apsm.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("size","");
        return list.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView amount;
        TextView dish_name;
TextView dishtime;
TextView tdrivewr;
        ImageView restimage;

        public ViewHolder(final View itemView) {
            super(itemView);

            dishtime = itemView.findViewById(R.id.dishtime);
            amount = itemView.findViewById(R.id.amount);
            tdrivewr = itemView.findViewById(R.id.tdrivewr);



        }
    }
}
