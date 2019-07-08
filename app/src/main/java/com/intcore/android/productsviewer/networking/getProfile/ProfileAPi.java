package com.intcore.android.productsviewer.networking.getProfile;

import com.intcore.android.productsviewer.data.user.OneUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfileAPi {
    @GET("api/v1/user/auth/get-profile")
    Call<OneUser> getProfile(@Query("api_token") String apiToken);
}
