package com.foodona.restaurent.Prefreance;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.foodona.restaurent.Beans.LoginBean;


/**
 * Created by Ram on 7/12/2016.
 */
public class AppPreferences {

    // App preference Name
    public static final String Prefsname = "FoodonaRestaurent";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL_ID = "email";
    public static final String KEY_MOBILE = "phone";
    public static final String KEY_RES_ID = "res_id";
    public static final String KEY_USER_ROLE = "role";
    public static final String KEY_USER_TOKEN = "token";
    public static final String KEY_USER_DEVICE_TYPE = "devie_type";

    public static Editor editor;


  /*  public static final String KEY_CART_QTY = "cart_qty";
    public static final String KEY_CART_AMOUNT = "cart_amount";
    public static final String KEY_LOGIN_ID = "profile_login_id";
    public static final String KEY_FIRST_NAME = "profile_first_name";
    public static final String KEY_LAST_NAME = "profile_last_name";
    public static final String KEY_USER_MOB = "profile_mob";*/

    public static void SaveUserdetail(Context ctx, LoginBean login_model) {

        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);

        Editor editor = prefs.edit();
        editor.putString(KEY_ID, login_model.getId());
        editor.putString(KEY_USERNAME, login_model.getUsername());
        editor.putString(KEY_EMAIL_ID, login_model.getEmail());
        editor.putString(KEY_MOBILE, login_model.getPhone());
        editor.putString(KEY_RES_ID, login_model.getRes_id());
        editor.putString(KEY_USER_ROLE, login_model.getRole());
        editor.putString(KEY_USER_TOKEN, login_model.getToken());
        editor.putString(KEY_USER_DEVICE_TYPE, login_model.getDevice_type());



        Log.d("nnnnn", login_model.getEmail() + login_model.getEmail() + login_model.getId() + "HHH" + login_model.getId());
        editor.commit();
    }


    public static LoginBean getSavedUser(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        LoginBean modal = new LoginBean();
        modal.setId(prefs.getString(KEY_ID, "-1"));
        modal.setUsername(prefs.getString(KEY_USERNAME, "-1"));
        modal.setEmail(prefs.getString(KEY_EMAIL_ID, "-1"));

        modal.setPhone(prefs.getString(KEY_MOBILE, "-1"));

        modal.setRes_id(prefs.getString(KEY_RES_ID, "-1"));
        modal.setRole(prefs.getString(KEY_USER_ROLE, "-1"));
        modal.setToken(prefs.getString(KEY_USER_TOKEN, "-1"));
        modal.setDevice_type(prefs.getString(KEY_USER_DEVICE_TYPE, "-1"));


        return modal;

    }

    public static void clearPrefsdata(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear().commit();

    }
}


