package com.foodona.restaurent.Beans;

public class LoginBean {
    String id;
    String username;
    String password;
    String phone;
    String email;
    String res_id;
    String role;
    String token;
    String device_type;
    String isactive;

    public String getIsactive(String s) {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public LoginBean() {
        this.id = id;
        this.username = username;

        this.phone = phone;
        this.email = email;
        this.res_id = res_id;
        this.role = role;
        this.token=token;
        this.device_type=device_type;
        this.isactive=isactive;

    }
}
