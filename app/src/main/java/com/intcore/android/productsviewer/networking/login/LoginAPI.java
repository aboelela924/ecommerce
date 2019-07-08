package com.intcore.android.productsviewer.networking.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import com.intcore.android.productsviewer.data.user.*;

public interface LoginAPI {
    @Headers({
            "Accept:application/json",
            "Content-Type:application/json"
    })
    @POST("api/v1/user/auth/signin")
    Call<OneUser> signInUser(@Body LoginBody body);
}
