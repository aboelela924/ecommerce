package com.intcore.android.productsviewer.presenters.loginPresenter;

import com.intcore.android.productsviewer.data.user.LoginBody;
import com.intcore.android.productsviewer.data.user.OneUser;
import com.intcore.android.productsviewer.data.user.User;
import com.intcore.android.productsviewer.networking.getProfile.ProfileNetowkring;
import com.intcore.android.productsviewer.networking.login.LoginNetworking;

public class LoginPresenter implements LoginNetworking.onLoad, ProfileNetowkring.OnDataLoad {
    private LoginView mLoginView;
    private LoginNetworking mNetworking;
    private ProfileNetowkring mProfileNetowkring;

    public LoginPresenter(LoginView loginView) {
        mLoginView = loginView;
        mNetworking = new LoginNetworking(this);
        mProfileNetowkring = new ProfileNetowkring(this);
    }

    public void login(LoginBody body){
        mNetworking.startFetching(body);
    }

    public void login(String apiToken){
        mProfileNetowkring.loadData(apiToken);
    }

    @Override
    public void onFinishLoading(OneUser user) {
        mLoginView.finishedLoading(user.getUser());
    }


    @Override
    public void onError(String errorMessage) {
        mLoginView.showError(errorMessage);
    }
}
