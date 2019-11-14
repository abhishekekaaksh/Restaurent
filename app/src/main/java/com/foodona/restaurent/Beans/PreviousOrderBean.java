package com.foodona.restaurent.Beans;

import com.google.gson.annotations.SerializedName;

public class PreviousOrderBean {
    /**
     * id : 100
     * user_id : 43
     * res_id : 43
     * address : jaipur
     * lat : 26.9124
     * long : 75.7873
     * phone : 9314497070
     * notes :
     * total_price : 400
     * deliveryboy_id : 19
     * payment : COD
     * created_at : 1572431559
     * notify : 0
     * status : 4
     * accept_date_time : null
     * accept_status : null
     * assign_date_time : null
     * assign_status : null
     * delivered_date_time : 06-11-2019 01:41
     * delivery_date_time : null
     * delivery_status : null
     * delivered_status : active
     * reject_date_time : null
     * reject_status : null
     * is_assigned : 19
     */

    private String id;
    private String user_id;
    private String res_id;
    private String address;
    private String lat;
    @SerializedName("long")
    private String longX;
    private String phone;
    private String notes;
    private String total_price;
    private String deliveryboy_id;
    private String payment;
    private String created_at;
    private String notify;
    @SerializedName("status")
    private String status;
    private Object accept_date_time;
    private Object accept_status;
    private Object assign_date_time;
    private Object assign_status;
    private String delivered_date_time;
    private Object delivery_date_time;
    private Object delivery_status;
    private String delivered_status;
    private Object reject_date_time;
    private Object reject_status;
    private String is_assigned;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongX() {
        return longX;
    }

    public void setLongX(String longX) {
        this.longX = longX;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getDeliveryboy_id() {
        return deliveryboy_id;
    }

    public void setDeliveryboy_id(String deliveryboy_id) {
        this.deliveryboy_id = deliveryboy_id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getAccept_date_time() {
        return accept_date_time;
    }

    public void setAccept_date_time(Object accept_date_time) {
        this.accept_date_time = accept_date_time;
    }

    public Object getAccept_status() {
        return accept_status;
    }

    public void setAccept_status(Object accept_status) {
        this.accept_status = accept_status;
    }

    public Object getAssign_date_time() {
        return assign_date_time;
    }

    public void setAssign_date_time(Object assign_date_time) {
        this.assign_date_time = assign_date_time;
    }

    public Object getAssign_status() {
        return assign_status;
    }

    public void setAssign_status(Object assign_status) {
        this.assign_status = assign_status;
    }

    public String getDelivered_date_time() {
        return delivered_date_time;
    }

    public void setDelivered_date_time(String delivered_date_time) {
        this.delivered_date_time = delivered_date_time;
    }

    public Object getDelivery_date_time() {
        return delivery_date_time;
    }

    public void setDelivery_date_time(Object delivery_date_time) {
        this.delivery_date_time = delivery_date_time;
    }

    public Object getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(Object delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getDelivered_status() {
        return delivered_status;
    }

    public void setDelivered_status(String delivered_status) {
        this.delivered_status = delivered_status;
    }

    public Object getReject_date_time() {
        return reject_date_time;
    }

    public void setReject_date_time(Object reject_date_time) {
        this.reject_date_time = reject_date_time;
    }

    public Object getReject_status() {
        return reject_status;
    }

    public void setReject_status(Object reject_status) {
        this.reject_status = reject_status;
    }

    public String getIs_assigned() {
        return is_assigned;
    }

    public void setIs_assigned(String is_assigned) {
        this.is_assigned = is_assigned;
    }
}
