package com.foodona.restaurent.Beans;

import com.foodona.restaurent.Modal.OrderResponse;

import java.util.ArrayList;

public class OrderBeans {
    private OrderResponse info;
    private String status;
    private String msg;
private ArrayList<OrderResponse>orderResponses=new ArrayList<>();

    /**
     * info : {"id":"11","user_id":"43","res_id":"42","address":"maheshnagar","lat":"26","long":"47","notes":"testing","total_price":"400","payment":"COD","created_at":"1567852747","notify":"0","status":"0","accept_date_time":null,"accept_status":null,"assign_date_time":null,"assign_status":null,"delivered_date_time":null,"delivery_date_time":null,"delivery_status":null,"delivered_status":null,"reject_date_time":null,"reject_status":null,"is_assigned":null}
     * status : Success
     * msg : Successfully
     */

    public OrderResponse getInfo() {
        return info;
    }

    public void setInfo(OrderResponse info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<OrderResponse> getOrderResponses() {
        return orderResponses;
    }

    public void setOrderResponses(ArrayList<OrderResponse> orderResponses) {
        this.orderResponses = orderResponses;
    }
}
