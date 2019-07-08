package com.intcore.android.productsviewer.networking.favourite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.intcore.android.productsviewer.data.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavouriteNetworking  {

    private static final String BASE_URL = "https://e-commerce-dev.intcore.net/";

    private OnAddLikeLoad mPresenter;

    public interface OnAddLikeLoad{
        void onAddToFavouriteResponse(Message message);
        void onAddToFavouriteFail(String message);;
    }



    public FavouriteNetworking(OnAddLikeLoad presenter) {
        mPresenter = presenter;
    }

    public void addToFavourite(FavouriteBody body){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FavouriteAPI favouriteAPI = retrofit.create(FavouriteAPI.class);
        Call<Message> result = favouriteAPI.addToFavourite(body);
        result.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()){
                    mPresenter.onAddToFavouriteResponse(response.body());
                }else{
                    mPresenter.onAddToFavouriteFail(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                mPresenter.onAddToFavouriteFail(t.getMessage());
            }
        });
    }




}
