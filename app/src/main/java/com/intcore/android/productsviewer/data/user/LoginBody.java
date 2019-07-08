package com.intcore.android.productsviewer.data.user;

public class LoginBody {
    String name;
    String password;
    int mobile_token;
    String os;

    public LoginBody(String name, String password, int mobile_token, String os) {
        this.name = name;
        this.password = password;
        this.mobile_token = mobile_token;
        this.os = os;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobile_token() {
        return mobile_token;
    }

    public void setMobile_token(int mobile_token) {
        this.mobile_token = mobile_token;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
