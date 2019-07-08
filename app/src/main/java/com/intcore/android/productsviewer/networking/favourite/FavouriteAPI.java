package com.intcore.android.productsviewer.networking.favourite;

import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.intcore.android.productsviewer.data.Message;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavouriteAPI {
    @Headers({
            "Accept:application/json",
            "Content-Type:application/json"
    })
    @POST("api/v1/user/app/action/favourite")
    Call<Message> addToFavourite(@Body FavouriteBody body);

    @GET("api/v1/user/auth/favourite")
    Call<Products> getFavourtieProducts(@Query("api_token") String apiToken);
}
