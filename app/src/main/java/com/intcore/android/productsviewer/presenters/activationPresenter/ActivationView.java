package com.intcore.android.productsviewer.presenters.activationPresenter;

import com.intcore.android.productsviewer.data.user.User;

public interface ActivationView {

    void onLoadFinish(User user);
    void onError(String errorMessage);
}
