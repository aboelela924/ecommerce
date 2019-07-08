package com.intcore.android.productsviewer.networking.activation;

import com.intcore.android.productsviewer.data.user.ActivationBody;
import com.intcore.android.productsviewer.data.user.OneUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ActivationAPI {
    @Headers({
            "Accept:application/json",
            "Content-Type:application/json"
    })
    @POST("api/v1/user/auth/active-account")
    Call<OneUser> checkActivation(@Body ActivationBody body);
}
