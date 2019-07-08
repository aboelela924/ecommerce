package com.intcore.android.productsviewer.networking.main;

import android.os.Process;

import com.intcore.android.productsviewer.data.Products;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EcommerceAPI {
    @GET("api/v1/user/app/home")
    Call<Products> getProducts(@Query("api_token") String apiToken);

    @GET("api/v1/user/app/home")
    Call<Products> getProducts();


}
