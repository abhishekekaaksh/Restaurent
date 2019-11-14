package com.foodona.restaurent.Activity;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foodona.restaurent.Beans.LoginBean;
import com.foodona.restaurent.MainActivity;
import com.foodona.restaurent.Modal.TokenModal;
import com.foodona.restaurent.Prefreance.AppPreferences;
import com.foodona.restaurent.R;
import com.foodona.restaurent.Response.LoginResponse;
import com.foodona.restaurent.rest.ApiClient;
import com.foodona.restaurent.rest.ApiInterface;
import com.google.firebase.iid.FirebaseInstanceId;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends AppCompatActivity implements View.OnClickListener {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ResponseBody result;
    EditText edit_email;
    EditText edit_password;
    Button btn_login;
    String Str_Email;
    String Str_Password;
    String Status;

    AVLoadingIndicatorView avi;
    String token;
    String NotificationIdd="";
    String User_ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
         token = FirebaseInstanceId.getInstance().getToken();
       // NotificationIdd = FirebaseInstanceId.getInstance().get();

        //Log.e("MYTAG",  token);
        User_ID = AppPreferences.getSavedUser(LoginFragment.this).getId();

        find();

    }


    public void find() {
        avi = findViewById(R.id.bar);
        edit_email = findViewById(R.id.email);
        edit_password = findViewById(R.id.password);
        btn_login = findViewById(R.id.email_sign_in_button);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_login) {
            Str_Email = edit_email.getText().toString().trim();
            Str_Password = edit_password.getText().toString().trim();
            if (Str_Email.equals("") || Str_Email.isEmpty()) {
                Toast.makeText(LoginFragment.this, "Please enter valid Email.", Toast.LENGTH_SHORT).show();
            } else if (!Str_Email.matches(emailPattern)) {
                Toast.makeText(LoginFragment.this, "Invalid Email.", Toast.LENGTH_SHORT).show();
            } else if (Str_Password.equals("") || Str_Password.isEmpty()) {
                Toast.makeText(LoginFragment.this, "Please enter password.", Toast.LENGTH_SHORT).show();
            } else if (Str_Password.length() < 5 || Str_Password.length() > 15) {
                Toast.makeText(LoginFragment.this, "Password should be 6 to 15 characters.", Toast.LENGTH_SHORT).show();
            } else {
                Login();
            }
        }
    }


    private void Login() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"email", "password"}, new String[]{Str_Email, Str_Password});
        Call<LoginResponse> call = apiInterface.Login(builder.build());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                avi.setVisibility(View.GONE);
                Log.d("11111", "11111");
                if (response.isSuccessful()) {
                    Log.d("2222", "2222");
                    try {
                        if (response.isSuccessful() && response.body().getInfo() != null) {
                            LoginBean loginBean = response.body().getInfo();

                             Status = loginBean.getIsactive("1");
                            if (Status.equals("1")) {
                                AppPreferences.SaveUserdetail(LoginFragment.this, loginBean);
                                Updatedevice();

                                startActivity(new Intent(LoginFragment.this, MainActivity.class));
                            } else {
                                Toast.makeText(LoginFragment.this, "Please Contact To admin", Toast.LENGTH_SHORT).show();
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    private void Updatedevice() {
        avi.setVisibility(View.VISIBLE);

       User_ID = AppPreferences.getSavedUser(LoginFragment.this).getId();

        //  startLoadingDialog(getString(R.string.please_wait));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        //  FormBody.Builder builder = ApiClient.createBuilder(new String[]{"email", "password"},
        //        new String[]{"test@test.com", "123456"});
        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {"user_id","token","device_type","user_type"},
                new String[]{User_ID,token,"android","2"});
        Call<ResponseBody> call = apiInterface.Token(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
                // public void onResponse(Response<ResponseBody> response, Retrofit retrofit){
                avi.setVisibility(View.GONE);
                Log.d("5555","55555");
                // stopLoadingDialog();
                if (response.isSuccessful())
                {
                    Log.d("6566","65666");
                    try {
                         result =  response.body();
                        Log.d("resut", String.valueOf(result));
                        JSONObject jsonObject =new JSONObject(String.valueOf(result));

                        //Log.d("msg",status);

                            JSONObject mJsonObject = jsonObject.optJSONObject("data");
                            TokenModal tokenModal = new TokenModal();
                        tokenModal.setSuccess(mJsonObject.getString("success"));
                        tokenModal.setMessage(mJsonObject.getString("message"));


                    }
 catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onApiFailure(call, t);

                Log.d("0000","0000");

                //Toast.makeText(LoginActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });



    }
    public void onApiFailure(Call<ResponseBody> call, Throwable t) {
        //Log.e("error", t.toString());
        avi.setVisibility(View.GONE);

        // stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException)){
           // Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();

        }
        else{
           // Toast.makeText(this, "Please try later", Toast.LENGTH_SHORT).show();

        }
    }



}
