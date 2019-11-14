package com.foodona.restaurent.rest;


import com.foodona.restaurent.Response.AcceptResponse;
import com.foodona.restaurent.Response.LoginResponse;
import com.foodona.restaurent.Response.Modal_Order_Response;
import com.foodona.restaurent.Response.PreviousOrderResponse;
import com.foodona.restaurent.Response.RevenueResponse;
import com.foodona.restaurent.Response.ReviewResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    // @POST("userlogin.php?")
    @POST("restaurant_ownerlogin.php?")
    Call<LoginResponse> Login(@Body RequestBody requestBody);

    @POST("restaurant_ownerorder.php?")
    Call<PreviousOrderResponse> PreviousOrder(@Body RequestBody requestBody);

    @POST("token.php?")
    Call<ResponseBody> Token(@Body RequestBody requestBody);

    @POST("order_details.php?")
    Call<Modal_Order_Response> OrderDetails(@Body RequestBody requestBody);

    @POST("verify_otp")
    Call<ResponseBody> Verify_otp(@Body RequestBody requestBody);

    @POST("forget")
    Call<ResponseBody> Forget_Pass(@Body RequestBody requestBody);

    @POST("order_complete.php")
    Call<AcceptResponse>OrderComplete(@Body RequestBody requestBody);

    @POST("restaurant_revenue.php?")
    Call<RevenueResponse> RestaurantRevenue(@Body RequestBody requestBody);

    @POST("restaurant_addcart.php?")
    Call<ResponseBody> Restaurant_add_Cart(@Body RequestBody requestBody);

    @POST("restaurant_getcart.php?")
    Call<ResponseBody> Restaurent_Get_Cart(@Body RequestBody requestBody);

    @POST("getrestaurant_review.php?")
    Call<ReviewResponse>RestaurentReview(@Body RequestBody requestBody);

    @POST("update-profile")
    Call<ResponseBody> Update_profile(@Body RequestBody requestBody);

    @POST("update-password")
    Call<ResponseBody> Update_password(@Body RequestBody requestBody);

    @POST("users-redeam")
    Call<ResponseBody> Users_redeam(@Body RequestBody requestBody);

    @POST("users-redeam-history")
    Call<ResponseBody> Users_Redeam_History(@Body RequestBody requestBody);

    @POST("groupusers")
    Call<ResponseBody> Groupusers(@Body RequestBody requestBody);

    @POST("addgroup")
    Call<ResponseBody> Addgroup(@Body RequestBody requestBody);

}
