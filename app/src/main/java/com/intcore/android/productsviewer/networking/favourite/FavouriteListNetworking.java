package com.intcore.android.productsviewer.networking.favourite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intcore.android.productsviewer.data.CategoryData.Products;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavouriteListNetworking {

    private static final String BASE_URL = "https://e-commerce-dev.intcore.net/";



    public interface OnLoadFavouriteList{
        void onListLoad(Products products);
        void onError(String message);
    }

    private OnLoadFavouriteList mPresenter;

    public FavouriteListNetworking(OnLoadFavouriteList presenter) {
        mPresenter = presenter;
    }

    public void getFavouriteList(String apiToken){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FavouriteAPI favouriteAPI = retrofit.create(FavouriteAPI.class);
        Call<Products> productsCall = favouriteAPI.getFavourtieProducts(apiToken);
        productsCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if(response.isSuccessful()){
                    mPresenter.onListLoad(response.body());
                }else{
                    mPresenter.onError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                mPresenter.onError(t.getMessage());
            }
        });
    }
}
