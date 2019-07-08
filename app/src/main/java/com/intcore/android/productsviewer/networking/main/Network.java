package com.intcore.android.productsviewer.networking.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intcore.android.productsviewer.presenters.mainPresenter.Presenter;
import com.intcore.android.productsviewer.data.Products;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network implements Callback<Products>{

    public interface OnDataLoad{
        void dataFetched(Products products);
        void onError(String message);
    }

    private static final String BASE_URL = "https://e-commerce-dev.intcore.net/";
    private Presenter mPresenter;

    public Network(Presenter presenter) {
        mPresenter = presenter;
    }

    public void loadData(String apiToken) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EcommerceAPI ecommerceAPI = retrofit.create(EcommerceAPI.class);

        Call<Products> call = ecommerceAPI.getProducts(apiToken);
        call.enqueue(this);
    }

    public void loadData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EcommerceAPI ecommerceAPI = retrofit.create(EcommerceAPI.class);

        Call<Products> call = ecommerceAPI.getProducts();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Products> call, Response<Products> response) {
        if(response.isSuccessful()){
            mPresenter.dataFetched(response.body());
        }else{
            mPresenter.onError(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<Products> call, Throwable t) {
        mPresenter.onError(t.getMessage());
    }
}
