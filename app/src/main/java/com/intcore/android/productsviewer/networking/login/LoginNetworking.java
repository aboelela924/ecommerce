package com.intcore.android.productsviewer.networking.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intcore.android.productsviewer.data.user.LoginBody;
import com.intcore.android.productsviewer.data.user.OneUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginNetworking implements Callback<OneUser> {
    private static final String BASE_URL = "https://e-commerce-dev.intcore.net/";
    private final static String TAG = "LoginNetworking";

    private onLoad mPresenter;

    public interface onLoad{
        void onFinishLoading(OneUser user);
        void onError(String errorMessage);
    }

    public LoginNetworking(onLoad presenter){
        mPresenter = presenter;
    }

    public void startFetching(LoginBody body){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        LoginAPI loginAPI = retrofit.create(LoginAPI.class);
        Call<OneUser> call = loginAPI.signInUser(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<OneUser> call, Response<OneUser> response) {
        OneUser user = response.body();
        mPresenter.onFinishLoading(user);
    }

    @Override
    public void onFailure(Call<OneUser> call, Throwable t) {

    }

}
