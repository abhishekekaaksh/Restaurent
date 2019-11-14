package com.foodona.restaurent.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;

import com.foodona.restaurent.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.foodona.restaurent.Prefreance.AppPreferences;
import com.foodona.restaurent.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    String id;

    View rootView;
Context pContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        id = AppPreferences.getSavedUser(SplashActivity.this).getId();
        Log.d("iiiid",id);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id.equals("-1")) {
                    Intent intent = new Intent(SplashActivity.this, LoginFragment.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();


                }


            }
        }, SPLASH_SCREEN_TIME_OUT);
    }




}
