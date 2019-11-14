package com.foodona.restaurent.Response;

import com.foodona.restaurent.Beans.PreviousOrderBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreviousOrderResponse {


    /**
     * info : {"id":"103","user_id":"53","res_id":"42","address":"jaipur","lat":"26","long":"47","notes":"","total_price":"400","deliveryboy_id":"","payment":"COD","created_at":"1572437692","notify":"0","status":"0","accept_date_time":null,"accept_status":null,"assign_date_time":null,"assign_status":null,"delivered_date_time":null,"delivery_date_time":null,"delivery_status":null,"delivered_status":null,"reject_date_time":null,"reject_status":null,"is_assigned":null}
     * status : Success
     * msg : Successfully
     */

    //private PreviousOrderBean info;
    private String status;
    private String msg;
    @SerializedName("info")
    private List<PreviousOrderBean> info;

    public List<PreviousOrderBean> getInfo() {
        return info;
    }

    public void setInfo(List<PreviousOrderBean> info) {
        this.info = info;
    }
/*  public List<PreviousOrderBean> getInfo() {
        return order;
    }

    public void setOrder(List<PreviousOrderBean> info) {
        this.order = order;
    }
*/

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


}
