package com.foodona.restaurent.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.foodona.restaurent.Adapter.ReviewAdapter;
import com.foodona.restaurent.Beans.ReviewBeans;
import com.foodona.restaurent.Prefreance.AppPreferences;
import com.foodona.restaurent.Response.ReviewResponse;
import com.foodona.restaurent.rest.ApiClient;
import com.foodona.restaurent.rest.ApiInterface;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.foodona.restaurent.R;
import java.util.ArrayList;
import java.util.List;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.foodona.restaurent.rest.AppUrls.Resturent_user_Image;

public class FragmentRestaurentReview extends Fragment {
    View rootView;
    String RESId;
    ReviewAdapter reviewAdapter;
    ReviewBeans reviewBeans;
    List<ReviewBeans> review_Beans_list;
    RecyclerView recyclerItemReview;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_restaurent_review, container, false);
        context = getActivity();
        review_Beans_list = new ArrayList<>();

        RESId = AppPreferences.getSavedUser(getActivity()).getRes_id(); // Res UserId.
        Log.e("UserId", "" + RESId);
        recyclerItemReview = (RecyclerView) rootView.findViewById(R.id.recyclerItemReview);
        getReviewApi();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Resturent Review");
        // ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("July 19 - July 26");
    }

    @Override
    public void onPause() {
        super.onPause();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
    }

    private void getReviewApi() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Log.d("reviewresponse1", "gg11111");

        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {"res_id"},
                new String[]{RESId});
        Call<ReviewResponse> call = apiInterface.RestaurentReview(builder.build());
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                // stopLoadingDialog();

                if (response.isSuccessful()) {
                    Log.d("reviewresponse", "gg11111");

                    if (response.body() != null && response.body().getReview() != null) {
                        Log.e("ReviewSizeinfo", "" + response.body().getReview().size());
                        for (int i = 0; i < response.body().getReview().size(); i++) {

                            reviewBeans = new ReviewBeans();
                            //reviewBeans.setImage(response.body().getReview().get(i).getImage());
                            reviewBeans.setUsername(response.body().getReview().get(i).getUsername());
                            Log.e("Reviewuser", "" + response.body().getReview().get(i).getUsername());
                            reviewBeans.setImage(Resturent_user_Image + response.body().getReview().get(i).getImage());
                            Log.e("Reviewuserimage", "" + Resturent_user_Image + response.body().getReview().get(i).getImage());

                            reviewBeans.setReview_text(response.body().getReview().get(i).getReview_text());
                            reviewBeans.setRatting(response.body().getReview().get(i).getRatting());
                            reviewBeans.setCreated_at(response.body().getReview().get(i).getCreated_at());

                            review_Beans_list.add(reviewBeans);

                        }


                        //setting adapter
                        reviewAdapter = new ReviewAdapter(context, review_Beans_list);
                        recyclerItemReview.setHasFixedSize(true);
                        recyclerItemReview.setLayoutManager(new LinearLayoutManager(context));
                        recyclerItemReview.setAdapter(reviewAdapter);
                    } else {
//                        Log.e("Sizeinfo1",""+response.body().getReviews().size());
                    }

                }
            }


            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                //onApiFailure(call, t);
                Log.d("Review0000", "0000");
                Toast.makeText(getActivity(), "PreviousOrder Something went wrong!.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
