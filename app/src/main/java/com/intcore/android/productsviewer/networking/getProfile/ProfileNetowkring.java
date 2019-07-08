package com.intcore.android.productsviewer.networking.getProfile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intcore.android.productsviewer.data.Products;
import com.intcore.android.productsviewer.data.user.OneUser;
import com.intcore.android.productsviewer.data.user.User;
import com.intcore.android.productsviewer.networking.main.EcommerceAPI;
import com.intcore.android.productsviewer.presenters.mainPresenter.Presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileNetowkring implements Callback<OneUser> {


    private static final String BASE_URL = "https://e-commerce-dev.intcore.net/";

    public interface OnDataLoad{
        void onFinishLoading(OneUser user);
        void onError(String message);
    }

    private  OnDataLoad mPresenter;
    public ProfileNetowkring(OnDataLoad presenter) {
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

        ProfileAPi profileAPi = retrofit.create(ProfileAPi.class);

        Call<OneUser> call = profileAPi.getProfile(apiToken);
        call.enqueue(this);
    }



    @Override
    public void onResponse(Call<OneUser> call, Response<OneUser> response) {
        if(response.isSuccessful()){
            mPresenter.onFinishLoading(response.body());
        }else{
            mPresenter.onError(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<OneUser> call, Throwable t) {
        mPresenter.onError(t.getMessage());
    }
}
