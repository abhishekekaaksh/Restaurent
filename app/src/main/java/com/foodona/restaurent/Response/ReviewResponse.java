package com.foodona.restaurent.Response;

import com.foodona.restaurent.Beans.ReviewBeans;

import java.util.List;

public class ReviewResponse {


    /**
     * success : 1
     * review : [{"id":"42","username":"mukesh","image":"","review_text":"good food","ratting":"4","created_at":"5092019","login_with":"ios"},{"id":"42","username":"mukesh","image":"","review_text":"good food","ratting":"4","created_at":"5092019","login_with":"ios"}]
     */

    private String success;
    private List<ReviewBeans> review;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ReviewBeans> getReview() {
        return review;
    }

    public void setReview(List<ReviewBeans> review) {
        this.review = review;
    }


}
