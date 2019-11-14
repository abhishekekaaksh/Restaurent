package com.foodona.restaurent.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.foodona.restaurent.R;

public class FragmentAddNewItem extends Fragment {

   View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view=inflater.inflate(R.layout.activity_add_new_item,container,false);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Add Menu | Add New Item");
        //  ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("July 19 - July 26");
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("");
    }
}
