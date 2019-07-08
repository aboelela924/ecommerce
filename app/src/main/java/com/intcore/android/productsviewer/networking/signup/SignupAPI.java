package com.intcore.android.productsviewer.networking.signup;

import com.intcore.android.productsviewer.data.user.OneUser;
import com.intcore.android.productsviewer.data.user.SignupBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SignupAPI {
    @Headers({
            "Accept:application/json",
            "Content-Type:application/json"
    })
    @POST("api/v1/user/auth/signup")
    Call<OneUser> signInUser(@Body SignupBody body);
}
