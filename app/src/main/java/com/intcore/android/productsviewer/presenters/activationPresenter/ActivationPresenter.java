package com.intcore.android.productsviewer.presenters.activationPresenter;

import com.intcore.android.productsviewer.data.user.ActivationBody;
import com.intcore.android.productsviewer.data.user.OneUser;
import com.intcore.android.productsviewer.networking.activation.ActivationNetworking;

public class ActivationPresenter implements ActivationNetworking.onLoad {
    private ActivationView mView;
    private ActivationNetworking mNetworking;

    public ActivationPresenter(ActivationView view) {
        mView = view;
        mNetworking = new ActivationNetworking(this);
    }

    public void activateAccount(ActivationBody body){
        mNetworking.startFetching(body);
    }

    @Override
    public void onFinishLoading(OneUser user) {
        mView.onLoadFinish(user.getUser());
    }

    @Override
    public void onError(String errorMessage) {
        mView.onError(errorMessage);
    }
}
