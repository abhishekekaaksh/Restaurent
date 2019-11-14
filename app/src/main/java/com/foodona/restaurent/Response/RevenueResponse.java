package com.foodona.restaurent.Response;

public class RevenueResponse {
    /**
     * success : 1
     * order : null
     * revenue : 400
     * total_order : 23
     * complete_order : 1
     * cancel_order : 0
     */

    private String success;
    private Object order;
    private int revenue;
    private int total_order;
    private int complete_order;
    private int cancel_order;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getTotal_order() {
        return total_order;
    }

    public void setTotal_order(int total_order) {
        this.total_order = total_order;
    }

    public int getComplete_order() {
        return complete_order;
    }

    public void setComplete_order(int complete_order) {
        this.complete_order = complete_order;
    }

    public int getCancel_order() {
        return cancel_order;
    }

    public void setCancel_order(int cancel_order) {
        this.cancel_order = cancel_order;
    }


    /**
     * success : 0
     * order :
     * revenue :
     */


}
