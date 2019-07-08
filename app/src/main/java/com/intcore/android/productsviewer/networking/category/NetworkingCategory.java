package com.intcore.android.productsviewer.networking.category;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intcore.android.productsviewer.presenters.CategoryPresenter.CategoryProductsPresenter;
import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.intcore.android.productsviewer.data.CategoryProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkingCategory implements Callback<CategoryProduct> {
    private static final String BASE_URL = "https://e-commerce-dev.intcore.net/";
    private CategoryProductsPresenter mCategoryresenter;

    public interface OnDataLoad{
        void dataFetched(Products products);
        void onError(String message);
    }

    public NetworkingCategory(CategoryProductsPresenter presenter) {
        mCategoryresenter = presenter;
    }

    public void loadCategoryproducts(String categoryId, int pageNumber){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CategoryAPI categoryAPI = retrofit.create(CategoryAPI.class);
        Call<CategoryProduct> categoryProductsCall = categoryAPI.getCategoryProducts(categoryId, pageNumber);
        categoryProductsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<CategoryProduct> call, Response<CategoryProduct> response) {
        if(response.isSuccessful()){
            mCategoryresenter.dataFetched(response.body().getProducts());
        }else {
            mCategoryresenter.onError(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<CategoryProduct> call, Throwable t) {
        String message = t.getMessage();
        mCategoryresenter.onError(message);
    }
}
