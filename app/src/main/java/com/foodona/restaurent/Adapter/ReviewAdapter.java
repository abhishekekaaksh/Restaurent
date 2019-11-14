package com.foodona.restaurent.Adapter;

import android.content.Context;
import android.os.Bundle;

import com.foodona.restaurent.Beans.ReviewBeans;
import com.foodona.restaurent.Response.ReviewResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodona.restaurent.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.foodona.restaurent.rest.AppUrls.Resturent_user_Image;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

     Context context;
    List<ReviewBeans> reviewResponses;

    // RecyclerView recyclerView;
    public ReviewAdapter(Context context, List<ReviewBeans> reviewResponses) {
        this.context = context;
        this.reviewResponses = reviewResponses;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.review_adapter, parent, false);
        ReviewAdapter.ViewHolder viewHolder = new ReviewAdapter.ViewHolder(listItem);
        return viewHolder;
    }




    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, final int position) {
        String imgurl = reviewResponses.get(position).getImage();

        holder.tv_username.setText(reviewResponses.get(position).getUsername());
        holder.tv_review.setText("Review : "+reviewResponses.get(position).getReview_text());
        holder.tv_create.setText(reviewResponses.get(position).getCreated_at());
        holder.tv_rating.setText(reviewResponses.get(position).getRatting());
        //Picasso.with(context).load(imgurl).placeholder(R.drawable.login_logo).error(R.drawable.login_logo).into(holder.userimage);

        Picasso.get().load(Resturent_user_Image+reviewResponses.get(position).getImage()).placeholder(R.drawable.login_logo).error(R.drawable.login_logo).into(holder.userimage);

       // Picasso.get().load(Resturent_user_Image+reviewResponses.get(position).getImage()).into(holder.userimage);
        // holder.tvItemName.setText(orderBeanList.get(position).getMenu_name());
        //  holder.tvItemQuantity.setText(orderBeanList.get(position).getItemQty());
        //  holder.tvItemPrice.setText("₹" + orderBeanList.get(position).getItemAmt());
    }

    @Override
    public int getItemCount() {
        return reviewResponses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userimage;
         TextView tv_username, tv_review, tv_rating, tv_create;
        public ViewHolder(View itemView) {
            super(itemView);
            this.userimage = (ImageView) itemView.findViewById(R.id.userimage);
            this.tv_username = (TextView) itemView.findViewById(R.id.tv_username);
            this.tv_review = (TextView) itemView.findViewById(R.id.tv_review);
            this.tv_rating = (TextView) itemView.findViewById(R.id.tv_rating);
            this.tv_create = (TextView) itemView.findViewById(R.id.tv_create);


        }
    }

}
