package com.foodona.restaurent.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.restaurent.Beans.PreviousOrderBean;
import com.foodona.restaurent.Modal.Modal_Order;
import com.foodona.restaurent.NotofiationModel.DeliveryboyBean;
import com.foodona.restaurent.NotofiationModel.OrderBean;
import com.foodona.restaurent.R;
import com.foodona.restaurent.Response.Modal_Order_Response;
import com.foodona.restaurent.rest.ApiClient;
import com.foodona.restaurent.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviousOrderAdapter extends RecyclerView.Adapter<PreviousOrderAdapter.ViewHolder>{

    private Context context;
    private List<PreviousOrderBean> prevOrderList;
    private List<DeliveryboyBean> deliveryboydata;


    public PreviousOrderAdapter(Context context,List<PreviousOrderBean> prevOrderList) {
        this.context = context;
        this.prevOrderList = prevOrderList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_previous_order_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvShopName.setText("Delivery Boy ID "+prevOrderList.get(position).getDeliveryboy_id());
        holder.tvAmount.setText("₹"+prevOrderList.get(position).getTotal_price());

        if((prevOrderList.get(position).getStatus()!=null)){
            if(prevOrderList.get(position).getStatus().equals("0")) {
                holder.tvOrderStatus.setText("Pending");
                holder.tvOrderStatus.setTextColor(context.getResources().getColor(R.color.white));
            } else if(prevOrderList.get(position).getStatus().equals("1")) {
                holder.tvOrderStatus.setText("Preparing");
                holder.tvOrderStatus.setTextColor(context.getResources().getColor(R.color.white));
            }
            else if(prevOrderList.get(position).getStatus().equals("2")) {
                holder.tvOrderStatus.setText("Boy Reach at restaurent");
                holder.tvOrderStatus.setTextColor(context.getResources().getColor(R.color.yellow));
            }  else if(prevOrderList.get(position).getStatus().equals("3")) {
                holder.tvOrderStatus.setText("Pick The Food");
                holder.tvOrderStatus.setTextColor(context.getResources().getColor(R.color.green));
            }  else if(prevOrderList.get(position).getStatus().equals("4")) {
                holder.tvOrderStatus.setText("Complete");
                holder.tvOrderStatus.setTextColor(context.getResources().getColor(R.color.green));
            }
        }

        holder.rlRowPreviousOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(prevOrderList.get(position).getId()!=null)
                    getOrderDetailApi(prevOrderList.get(position).getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prevOrderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvShopName,tvTime,tvAmount,tvOrderStatus;
        public RelativeLayout rlRowPreviousOrder;

        public ViewHolder(View itemView) {
            super(itemView);

            this.tvShopName = (TextView) itemView.findViewById(R.id.tvShopName);
            this.tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            this.tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
            this.tvOrderStatus = (TextView) itemView.findViewById(R.id.tvOrderStatus);
            this.rlRowPreviousOrder = (RelativeLayout) itemView.findViewById(R.id.rlRowPreviousOrder);
        }
    }

    private void getOrderDetailApi(String orderId, final int position) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {"order_id"},
                new String[]{orderId});
        Call<Modal_Order_Response> call = apiInterface.OrderDetails(builder.build());
        call.enqueue(new Callback<Modal_Order_Response>() {
            @Override
            public void onResponse(Call<Modal_Order_Response> call, Response<Modal_Order_Response> response){
                // stopLoadingDialog();
                Log.d("getorderee","11111");
                if (response.isSuccessful())
                {
                    try {
                        if(response.body()!=null && response.body().getOrder_details()!=null);
                            showOrderDetailDialog(response.body().getOrder(),position);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<Modal_Order_Response> call, Throwable t) {
                //onApiFailure(call, t);

                Log.d("0000getorder","0000");
                Toast.makeText(context, "Something went wrong!.", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void showOrderDetailDialog(List<OrderBean> orderBeanList, int position) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.detail_order_layout);
        dialog.setCancelable(true);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        // set the custom dialog components - text, image and button
        TextView tvOrderStaus = (TextView) dialog.findViewById(R.id.tvOrderStaus);
        TextView tvOrderDate = (TextView) dialog.findViewById(R.id.tvOrderDate);
        TextView delivery=dialog.findViewById(R.id.delivery);
        
        RecyclerView recyclerItemDetail = (RecyclerView) dialog.findViewById(R.id.recyclerItemDetail);

        if(prevOrderList.get(position).getStatus().equals("4"))
            tvOrderStaus.setText("Delivered");
        else if(prevOrderList.get(position).getStatus().equals("5"))
            tvOrderStaus.setText("Cancelled");

        DetailOrderAdapter detailOrderAdapter = new DetailOrderAdapter(context,orderBeanList);
        recyclerItemDetail.setHasFixedSize(true);
        recyclerItemDetail.setLayoutManager(new LinearLayoutManager(context));
        recyclerItemDetail.setAdapter(detailOrderAdapter);

        dialog.show();
    }

}
