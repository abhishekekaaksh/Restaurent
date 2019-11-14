package com.foodona.restaurent;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.restaurent.Activity.LoginFragment;
import com.foodona.restaurent.Activity.SplashActivity;
import com.foodona.restaurent.Adapter.AdapterShowAcceptOrder;
import com.foodona.restaurent.Fragment.FragmentAddNewItem;
import com.foodona.restaurent.Fragment.FragmentOffer;
import com.foodona.restaurent.Fragment.FragmentPastOrder;
import com.foodona.restaurent.Fragment.FragmentReceivedOrder;
import com.foodona.restaurent.Fragment.FragmentRestaurentReview;
import com.foodona.restaurent.Fragment.FragmentRevenue;
import com.foodona.restaurent.Modal.OrderResponse;
import com.foodona.restaurent.NotofiationModel.OrderBean;
import com.foodona.restaurent.Prefreance.AppPreferences;
import com.foodona.restaurent.Response.AcceptResponse;
import com.foodona.restaurent.Response.Modal_Order_Response;
import com.foodona.restaurent.rest.ApiClient;
import com.foodona.restaurent.rest.ApiInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String Order_id;
    //  Fragment fragment;
    RecyclerView recyclerView;
    Button accept;
    Button reject;
    TextView tvorderId;
    DrawerLayout drawerLayout;
    LinearLayout left_drawer_left;
    Toolbar toolbar;
    Dialog pickUpOrderDialog;
    TextView tvHome, tvCreateMenu, tvReceivedOrder, tvPreviousOrders, tvManageRevenue, tvUserFeedback, tvLogout,tvUserReview;
    String messagef;
    View rootView;
    String data;
    ActionBar actionBar;
    String StrLat;
    String Strlng;
    String Str_OrderStatus;
    String UserID;
    /////
    OrderResponse orderBean;
    List<OrderBean> orderList;
    String strResId;
    AdapterShowAcceptOrder adapterOrder;

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // actionBar = getSupportActionBar();
        //   showPickUpOrderDialog();
        UserID = AppPreferences.getSavedUser(MainActivity.this).getId();
        left_drawer_left = findViewById(R.id.left_drawer_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        tvHome = findViewById(R.id.tvhome);

        //tvHome = (TextView)findViewById(R.id.tvHome);
        tvCreateMenu = findViewById(R.id.tvCreateMenu);
        tvUserReview = findViewById(R.id.tvUserReview);

        tvReceivedOrder = findViewById(R.id.tvReceivedOrder);
        tvPreviousOrders = findViewById(R.id.tvPreviousOrders);
        tvManageRevenue = findViewById(R.id.tvManageRevenue);
        tvUserFeedback = findViewById(R.id.tvUserFeedback);
        tvLogout = findViewById(R.id.tvLogout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);

        // getLocation();
        //open drawer on navigation button click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        tvHome.setOnClickListener(this);
        tvCreateMenu.setOnClickListener(this);
        tvPreviousOrders.setOnClickListener(this);
        tvReceivedOrder.setOnClickListener(this);
        tvManageRevenue.setOnClickListener(this);
        tvUserFeedback.setOnClickListener(this);
        tvUserReview.setOnClickListener(this);

        tvLogout.setOnClickListener(this);




    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getExtras() != null) {
            Log.e("intent", "yes " + getIntent().getExtras());
            Log.e("datas", "" + getIntent().getExtras().getString("NOTIFICATION_DATA"));

            if (getIntent().getExtras().getString("NOTIFICATION_DATA") != null) {
                Order_id = getIntent().getExtras().getString("NOTIFICATION_DATA");
                Log.e("Order_idD", Order_id);
                Bundle bundle = new Bundle();
                bundle.putString("Order_ID", Order_id);
                // set Fragmentclass Arguments
                FragmentReceivedOrder fragobj = new FragmentReceivedOrder();
                fragobj.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragobj).commit();

                //setting fragment on home
                getFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragobj).addToBackStack(null).commit();
                //getnOrdersApi();
                if (!Order_id.equals("null")){
                    Log.e("11111", "11111");
                    getnOrdersApi();
                }


            }

        }
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(left_drawer_left)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public void onClick(View view) {
        if (view == tvCreateMenu) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentAddNewItem) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentAddNewItem()).addToBackStack(null).commit();
            }
        } else if (view == tvReceivedOrder) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentOffer) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentOffer()).addToBackStack(null).commit();
            }
        } else if (view == tvPreviousOrders) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentPastOrder) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentPastOrder()).addToBackStack(null).commit();
            }
        } else if (view == tvManageRevenue) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentRevenue) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentRevenue()).addToBackStack(null).commit();
            }
        }else if (view == tvHome) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentReceivedOrder) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentReceivedOrder()).addToBackStack(null).commit();
            }
        } else if (view == tvUserReview) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentRestaurentReview) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentRestaurentReview()).addToBackStack(null).commit();
            }
        }else if (view == tvLogout) {
            AppPreferences.clearPrefsdata(MainActivity.this);

            startActivity(new Intent(MainActivity.this, LoginFragment.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);

    }


    private void showPickUpOrderDialog() {
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // custom dialog
        pickUpOrderDialog = new Dialog(this);
        pickUpOrderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pickUpOrderDialog.setContentView(R.layout.activity_show_order);
        orderList = new ArrayList<>();
        pickUpOrderDialog.setCancelable(false);
        Window window = pickUpOrderDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        // toolbar = (Toolbar) pickUpOrderDialog.findViewById(R.id.toolbar);
        recyclerView = pickUpOrderDialog.findViewById(R.id.recycleoffer);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        tvorderId = pickUpOrderDialog.findViewById(R.id.tvorderId);
        accept = pickUpOrderDialog.findViewById(R.id.accept);
        reject = pickUpOrderDialog.findViewById(R.id.reject);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AcceptOrderComplet();
                pickUpOrderDialog.dismiss();





            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUpOrderDialog.dismiss();

            }
        });
        pickUpOrderDialog.show();

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
                Log.e("orderres", "11111");

                if (response.isSuccessful()&& response.body().getSuccess().equals("1") ){
                    StrLat=response.body().getOrder_details().getRestaurant_lat();
                    Log.e("restaurentlat", "" + response.body().getOrder_details().getRestaurant_lat());
                    Str_OrderStatus=response.body().getOrder_details().getOrder_status();
                    Strlng=response.body().getOrder_details().getRestaurant_lng();
                    showPickUpOrderDialog();
                    orderList = response.body().getOrder();
                    for (int i=0;i<response.body().getOrder().size();i++){
                        response.body().getOrder().get(i).getMenu_name() ;
                        response.body().getOrder().get(i).getItemQty();

                    }



                    Log.e("orderressize", "" + orderList.size());
                    Log.e("orderressizeresponse", "" + response.body());
                    adapterOrder = new AdapterShowAcceptOrder(MainActivity.this, orderList);
                    recyclerView.setAdapter(adapterOrder);


                } else {
                    Log.e("orderressize", "" + "else");
                }
            }

            @Override
            public void onFailure(Call<Modal_Order_Response> call, Throwable t) {
                onApiFailure(call, t);
                Log.e("99595", "959595");


            }
        });
    }

    public void onApiFailure(Call<Modal_Order_Response> call, Throwable t) {

        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Please try later", Toast.LENGTH_SHORT).show();
    }





    private void AcceptOrderComplet() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"order_id","user_type","order_status","r_lat","r_lng"}, new
                String[]{Order_id,"2",Str_OrderStatus,StrLat,Strlng});
        Log.e("acceptorder", "11111");

        Call<AcceptResponse> call = apiInterface.OrderComplete(builder.build());
        call.enqueue(new Callback<AcceptResponse>() {
            @Override
            public void onResponse(Call<AcceptResponse> call, Response<AcceptResponse> response) {
                Log.e("acceptorderes", "11111");

                if (response.isSuccessful()&& response.body().getSuccess().equals("1") ){



                } else {
                    Log.e("acceptorderssize", "" + "else");
                }
            }

            @Override
            public void onFailure(Call<AcceptResponse> call, Throwable t) {
                Log.e("acceptorder5", "959595");


            }
        });
    }


}
