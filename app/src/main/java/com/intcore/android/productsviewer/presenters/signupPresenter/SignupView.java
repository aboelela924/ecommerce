package com.intcore.android.productsviewer.presenters.signupPresenter;

import com.intcore.android.productsviewer.data.user.User;

public interface SignupView {

    void onFinishLoading(User user);
    void onError(String errorMessage);
}
