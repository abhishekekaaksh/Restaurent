package com.foodona.restaurent.Response;

import com.foodona.restaurent.Beans.LoginBean;

public class LoginResponse {


    /**
     * info : {"id":"53","username":"KINGS FOOD","password":"kings.palace","phone":"9848687852","email":"kings.palace@gmail.com","res_id":"43","role":"2","token":"","device_type":""}
     * status : Success
     * msg : Successfully
     */

    private LoginBean info;
    private String status;
    private String msg;

    public LoginBean getInfo() {
        return info;
    }

    public void setInfo(LoginBean info) {
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

    public static class InfoBean {
    }
}
