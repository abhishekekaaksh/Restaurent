package com.foodona.restaurent.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.restaurent.Adapter.PreviousOrderAdapter;
import com.foodona.restaurent.Beans.PreviousOrderBean;
import com.foodona.restaurent.Prefreance.AppPreferences;
import com.foodona.restaurent.R;
import com.foodona.restaurent.Response.PreviousOrderResponse;
import com.foodona.restaurent.rest.ApiClient;
import com.foodona.restaurent.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPastOrder extends Fragment {
    View rootView;
    String RESId;
    PreviousOrderAdapter previousOrderAdapter;
    PreviousOrderBean previousOrderBean;
    List<PreviousOrderBean> prevOrderList;
    RecyclerView rlPreviousOrder;
    Context context;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.previous_order_layout, container, false);
        context = getActivity();
        prevOrderList = new ArrayList<>();

        RESId = AppPreferences.getSavedUser(getActivity()).getRes_id(); // Res UserId.
        Log.e("UserId", "" + RESId);

        rlPreviousOrder = (RecyclerView) rootView.findViewById(R.id.rlPreviousOrder);


        getPreviousOrdersApi();

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Previous Order");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("July 19 - July 26");
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
    }

    private void getPreviousOrdersApi() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {"res_id"},
                new String[]{RESId});
        Call<PreviousOrderResponse> call = apiInterface.PreviousOrder(builder.build());
        call.enqueue(new Callback<PreviousOrderResponse>() {
            @Override
            public void onResponse(Call<PreviousOrderResponse> call, Response<PreviousOrderResponse> response) {
                // stopLoadingDialog();

                if (response.isSuccessful()) {
                    Log.d("gg11111", "gg11111");

                        if (response.body() != null && response.body().getInfo() != null) {
                            Log.e("Sizeinfo",""+response.body().getInfo().size());
                            for (int i = 0; i < response.body().getInfo().size(); i++) {
                                previousOrderBean = new PreviousOrderBean();
                                previousOrderBean.setTotal_price(response.body().getInfo().get(i).getTotal_price());
                                previousOrderBean.setStatus(response.body().getInfo().get(i).getStatus());
                                previousOrderBean.setId(response.body().getInfo().get(i).getId());
                                previousOrderBean.setDeliveryboy_id(response.body().getInfo().get(i).getDeliveryboy_id());

                                prevOrderList.add(previousOrderBean);

                            }



                            //setting adapter
                            previousOrderAdapter = new PreviousOrderAdapter(context, prevOrderList);
                            rlPreviousOrder.setHasFixedSize(true);
                            rlPreviousOrder.setLayoutManager(new LinearLayoutManager(context));
                            rlPreviousOrder.setAdapter(previousOrderAdapter);
                        }else {
                            Log.e("Sizeinfo1",""+response.body().getInfo().size());
                        }

                    }
                }


            @Override
            public void onFailure(Call<PreviousOrderResponse> call, Throwable t) {
                //onApiFailure(call, t);
                Log.d("0000", "0000");
                Toast.makeText(getActivity(), "PreviousOrder Something went wrong!.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
