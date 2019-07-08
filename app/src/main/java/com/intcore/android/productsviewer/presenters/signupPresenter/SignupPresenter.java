package com.intcore.android.productsviewer.presenters.signupPresenter;

import com.intcore.android.productsviewer.data.user.OneUser;
import com.intcore.android.productsviewer.data.user.SignupBody;
import com.intcore.android.productsviewer.networking.signup.SignupNetworking;

public class SignupPresenter implements SignupNetworking.onLoad{
    private SignupView mView;
    private SignupNetworking mNetworking;

    public SignupPresenter(SignupView view) {
        mView = view;
        mNetworking = new SignupNetworking(this);
    }

    public void startLoading(SignupBody body){
        mNetworking.startFetching(body);
    }

    @Override
    public void onFinishLoading(OneUser user) {
        mView.onFinishLoading(user.getUser());
    }

    @Override
    public void onError(String errorMessage) {
        mView.onError(errorMessage);
    }
}
