package com.intcore.android.productsviewer.data.user;

public class ActivationBody {
    String code;
    String api_token;

    public ActivationBody(String code, String api_token) {
        this.code = code;
        this.api_token = api_token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
