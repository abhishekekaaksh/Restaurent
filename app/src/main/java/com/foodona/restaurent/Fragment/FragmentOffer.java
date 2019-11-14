package com.foodona.restaurent.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.restaurent.Adapter.AdapterOffer;
import com.foodona.restaurent.Modal.Modal_Order;
import com.foodona.restaurent.R;

import java.util.ArrayList;

public class FragmentOffer extends Fragment  {
    private  FragmentActivity activity;
    ArrayList<Modal_Order> modalorder;
    ImageView back;
    Modal_Order modal_order;
    AdapterOffer adapterOffer;
    RecyclerView recyclerView;
    View rootView;
    RecyclerView.LayoutManager layoutManager;
    private String[] order_num = new String[]{"Lootera", "less 50", "dth reacharge",
            "mobile Reachargee ", "choclate Reacharge", "hwa loot", "secure loot", "powwe", "sujnirise", "secure",
            "mobile Reachargee ","Tv reacharge",
            "hwa loot", "secure loot", "powwe", "sujnirise", "secure"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragmentoffer_rece, container, false);

        find();

        return rootView;

    }

    public void find() {
        recyclerView = rootView.findViewById(R.id.recycler);
        back = rootView.findViewById(R.id.back);
        recyclerView.setHasFixedSize(true);
        //  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.addItemDecoration(new DividerItemDecoration(OfferActivity.this, DividerItemDecoration.VERTICAL));
        ArrayList<Modal_Order> foodversins = preparedata();
        adapterOffer = new AdapterOffer(getActivity(), foodversins);
        recyclerView.setAdapter(adapterOffer);
       // back.setOnClickListener(this);
    }
    private ArrayList<Modal_Order> preparedata() {
        ArrayList<Modal_Order> modalOrders = new ArrayList<>();
        for (int i = 0; i < order_num.length; i++) {
            Modal_Order modalOrder = new Modal_Order();
            modalOrder.setOrder(order_num[i]);
            modalOrders.add(modalOrder);
        }
        return modalOrders;
    }

 /*   @Override
    public void onClick(View v) {
        if (v==back){
            activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }*/


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Received Order");
      //  ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("July 19 - July 26");
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("");
    }
}
