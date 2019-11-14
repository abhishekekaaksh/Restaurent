package com.foodona.restaurent.Response;

import com.foodona.restaurent.NotofiationModel.DeliveryboyBean;
import com.foodona.restaurent.NotofiationModel.OrderBean;
import com.foodona.restaurent.NotofiationModel.OrderDetailsBean;

import java.io.Serializable;
import java.util.List;

public class Modal_Order_Response implements Serializable {


    /**
     * success : 1
     * order_details : {"0":"97","order_id":"97","1":"44","res_id":"44","2":"NEAR CABWALA OFFICE","restaurant_address":"NEAR CABWALA OFFICE","3":"123","order_amount":"123","4":"0","order_status":"0","5":"43","user_id":"43","6":"Jaipur","user_address":"Jaipur","7":"26.9148067","user_lat":"26.9148067","8":"75.7937756","user_long":"75.7937756","9":"1572423802","created_at":"1572423802","10":null,"accept_date_time":null,"11":null,"accept_status":null,"12":null,"delivery_date_time":null,"13":null,"delivery_status":null,"14":null,"delivered_date_time":null,"15":null,"reject_date_time":null,"16":null,"reject_status":null,"17":"44","id":"44","18":"AHAA BIRYANI","restaurant_name":"AHAA BIRYANI","19":"26.9124","restaurant_lat":"26.9124","20":"resto_1548076648.jpg","photo":"resto_1548076648.jpg","21":"75.7873","restaurant_lng":"75.7873","22":"8540899999","phone":"8540899999","23":"29","delivery_time":"29"}
     * order : [{"order_id":"97","ItemId":"250","ItemQty":"6","ItemAmt":"150","menu_name":"VEG BIRYANI"},{"order_id":"97","ItemId":"249","ItemQty":"6","ItemAmt":"150","menu_name":"EGG BIRYANI"},{"order_id":"97","ItemId":"4","ItemQty":"8","ItemAmt":"210","menu_name":"MUTTON BIRYANI"}]
     * restaurant_name : AHAA BIRYANI
     */

    private String success;
private DeliveryboyBean deliveryboy;
    private OrderDetailsBean order_details;
    private String restaurant_name;
    private List<OrderBean> order;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public OrderDetailsBean getOrder_details() {
        return order_details;
    }

    public void setOrder_details(OrderDetailsBean order_details) {
        this.order_details = order_details;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }
}


