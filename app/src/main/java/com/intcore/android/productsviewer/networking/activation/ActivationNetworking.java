package com.intcore.android.productsviewer.networking.activation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intcore.android.productsviewer.data.user.ActivationBody;
import com.intcore.android.productsviewer.data.user.OneUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivationNetworking implements Callback<OneUser> {

    private static final String BASE_URL = "https://e-commerce-dev.intcore.net/";
    private final static String TAG = "ActivationNetworking";

    private ActivationNetworking.onLoad mPresenter;

    public interface onLoad{
        void onFinishLoading(OneUser user);
        void onError(String errorMessage);
    }

    public ActivationNetworking(ActivationNetworking.onLoad presenter){
        mPresenter = presenter;
    }

    public void startFetching(ActivationBody body){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ActivationAPI loginAPI = retrofit.create(ActivationAPI.class);
        Call<OneUser> call = loginAPI.checkActivation(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<OneUser> call, Response<OneUser> response) {
        if(response.isSuccessful()){
            OneUser user = response.body();
            mPresenter.onFinishLoading(user);
        }else{
            mPresenter.onError(response.errorBody().toString());
        }

    }

    @Override
    public void onFailure(Call<OneUser> call, Throwable t) {
        mPresenter.onError(t.getMessage());
    }

}
