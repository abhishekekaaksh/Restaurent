package com.foodona.restaurent.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.restaurent.Adapter.AdapterReceOrder;
import com.foodona.restaurent.Modal.OrderResponse;
import com.foodona.restaurent.NotofiationModel.OrderBean;
import com.foodona.restaurent.Prefreance.AppPreferences;
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

public class FragmentReceivedOrder extends Fragment {
    TextView order_iid;
    TextView order_status;
    TextView order_arriving;

    String Order_id = "";
    String StrLat;
    String Strlng;
    String Str_OrderStatus;
    String UserID;
    OrderResponse orderResponse;
    //ArrayList<OrderResponse> orderList;
    String strResId;
    AdapterReceOrder adapterOrder;
    RecyclerView recyclerView;
    AdapterReceOrder adapterReceOrder;
    View rootView;
    String MParam;
    //List<Modal_Order_Response>list;
    List<OrderBean> orderList;
    RecyclerView.LayoutManager layoutManager;
    String Notif;
    String text;
    String textMenu;
    OrderBean orderBean;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rec_order, container, false);
        orderList = new ArrayList<>();
        //list = new ArrayList<>();


        if (this.getArguments() != null) {
            Order_id = getArguments().getString("Order_ID");
            Log.e("Order_IDFragments", "" + Order_id);
            getnOrdersApi();
        } else {
            Log.e("123", "123");
        }

        strResId = AppPreferences.getSavedUser(getActivity()).getRes_id();


        find();


        return rootView;

    }


    public void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        String notificationMessage = extras.getString("FLAG_NOTIFICATION", "true");
    }

    public void find() {
        order_iid = rootView.findViewById(R.id.order_iid);
        order_status = rootView.findViewById(R.id.order_status);

        order_arriving = rootView.findViewById(R.id.order_arriving);

        recyclerView = rootView.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    private void getnOrdersApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"order_id"}, new
                String[]{Order_id});
        Log.e("555521", "11111");

        Call<Modal_Order_Response> call = apiInterface.OrderDetails(builder.build());
        call.enqueue(new Callback<Modal_Order_Response>() {
            @Override
            public void onResponse(Call<Modal_Order_Response> call, Response<Modal_Order_Response> response) {
                Log.e("orderre1s", "11111");

                if (response.isSuccessful() && response.body().getSuccess().equals("1")) {
                    // StrLat=response.body().getOrder_details().getRestaurant_lat();
                    // Log.e("restaurent1lat", "" + response.body().getOrder_details().getRestaurant_lat());
                    // Str_OrderStatus=response.body().getOrder_details().getOrder_status();
                    // Strlng=response.body().getOrder_details().getRestaurant_lng();
                    //showPickUpOrderDialog();
                    String str_orderstatus = response.body().getOrder_details().getOrder_status();
                    String str_deliverytime = response.body().getOrder_details().getDelivery_time();
                    String str_order_id = response.body().getOrder_details().getOrder_id();
                    //orderList = response.body().getOrder();

                    for (int i = 0; i < response.body().getOrder().size(); i++) {
                        orderBean = new OrderBean();
                        Log.e("orderressizere1sponse", "" + response.body().getOrder().get(i).getMenu_name());
                        orderBean.setMenu_name(response.body().getOrder().get(i).getMenu_name());
                        orderBean.setItemQty((response.body().getOrder().get(i).getItemQty()));
                        orderBean.setItemAmt((response.body().getOrder().get(i).getItemAmt()));
                        orderList.add(orderBean);
                   }

                    order_iid.setText("ID :  " + str_order_id);
                    order_status.setText("Status :  " + str_orderstatus);
                    order_arriving.setText("Time :  " + str_deliverytime);
                    Log.e("orderressiz1e", "" + orderList.size());
                    adapterReceOrder = new AdapterReceOrder(getActivity(), orderList);
                    recyclerView.setAdapter(adapterReceOrder);
               /*  adapter_rec_order_details =new Adapter_Rec_Order_details(getActivity(),orderList,str_orderstatus,str_deliverytime,str_order_id);
                 recyclerView.setAdapter(adapter_rec_order_details);*/


                } else {
                    Log.e("orderress1ize", "" + "else");
                }
            }

            @Override
            public void onFailure(Call<Modal_Order_Response> call, Throwable t) {
                onApiFailure(call, t);
                Log.e("995195", "959595");

                //  Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void onApiFailure(Call<Modal_Order_Response> call, Throwable t) {


        // stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
    }


    /*private void getOrdersApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"res_id"}, new
                String[]{strResId});

        Call<OrderBeans> call = apiInterface.ResturentOrder(builder.build());
        call.enqueue(new Callback<OrderBeans>() {
            @Override
            public void onResponse(Call<OrderBeans> call, Response<OrderBeans> response) {
                Log.e("11111", "11111");
                if (response.isSuccessful()) {
                    orderList = new ArrayList<>(Arrays.asList(response.body().getInfo()));
                    //orderList.add(response.body().getInfo());
                    //orderList=new ArrayList<>();
                    Log.e("size", "" + orderList.size());
                    Log.e("sizeresponse", "" + response.body());
                    adapterOrder = new AdapterReceOrder(getActivity(), orderList);
                    recyclerView.setAdapter(adapterOrder);


                } else {
                    Log.e("size", "" + "else");
                }
            }

            @Override
            public void onFailure(Call<OrderBeans> call, Throwable t) {
                onApiFailure(call, t);
                Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void onApiFailure(Call<OrderBeans> call, Throwable t) {
        //Log.e("error", t.toString());
        //avi.setVisibility(View.GONE);
        //swipeRefreshLayout.setRefreshing(false);

        // stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
    }*/


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Order");
        //  ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("July 19 - July 26");
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
    }
}
