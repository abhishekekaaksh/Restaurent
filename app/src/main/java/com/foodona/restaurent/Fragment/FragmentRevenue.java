package com.foodona.restaurent.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.foodona.restaurent.Prefreance.AppPreferences;
import com.foodona.restaurent.R;
import com.foodona.restaurent.Response.RevenueResponse;
import com.foodona.restaurent.rest.ApiClient;
import com.foodona.restaurent.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRevenue extends Fragment {
    AVLoadingIndicatorView loading;
    TextView tvcomplete_order;
    TextView tvcancelorder;
    TextView tvtotalorder;
    TextView tvtotalrevenue;


    View rootView;
    String RESId;
    String StrTotalOrder;
    String StrRevenue;
    String StrCompleteOrder;
    String StrCancelOrder;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_revenue, container, false);
        // context = getActivity();
        // prevOrderList = new ArrayList<>();


        RESId = AppPreferences.getSavedUser(getActivity()).getRes_id(); // Res UserId.
        Log.e("UserId", "" + RESId);
        loading=rootView.findViewById(R.id.loading);
        tvcomplete_order = rootView.findViewById(R.id.tvcomplete_order);
        tvcancelorder = rootView.findViewById(R.id.tvcancelorder);
        tvtotalorder = rootView.findViewById(R.id.tvtotalorder);
        tvtotalrevenue = rootView.findViewById(R.id.tvtotalrevenue);


        getReviewApi();
        // getPreviousOrdersApi();

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Total Revenue");
        // ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("July 19 - July 26");
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
    }

    private void getReviewApi() {
        loading.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {"res_id"},
                new String[]{RESId});
        Call<RevenueResponse> call = apiInterface.RestaurantRevenue(builder.build());
        call.enqueue(new Callback<RevenueResponse>() {
            @Override
            public void onResponse(Call<RevenueResponse> call, Response<RevenueResponse> response) {
                // stopLoadingDialog();
                loading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Log.d("gg11111", "gg11111");

                    if (response.body() != null && response.body().getSuccess() != null) {
                        // Log.e("Sizeinfo",""+response.body().getInfo().size());
                        StrCompleteOrder = String.valueOf(response.body().getComplete_order());
                        StrCancelOrder = String.valueOf(response.body().getCancel_order());
                        StrTotalOrder = String.valueOf(response.body().getTotal_order());
                        StrRevenue = String.valueOf(response.body().getRevenue());
                        tvcomplete_order.setText("Complete Order "+"\n" + StrCompleteOrder);
                        tvcancelorder.setText("Cancel Order "+"\n"  + StrCancelOrder);
                        tvtotalorder.setText("Total Order " +"\n" + StrTotalOrder);
                        tvtotalrevenue.setText("Total Revenue "+"\n"  +"₹ "+ StrRevenue);
                    } else {

                    }

                }
            }


            @Override
            public void onFailure(Call<RevenueResponse> call, Throwable t) {
                //onApiFailure(call, t);
                Log.d("0000", "0000");
                Toast.makeText(getActivity(), "PreviousOrder Something went wrong!.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
