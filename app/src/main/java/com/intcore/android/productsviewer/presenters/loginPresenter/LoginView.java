package com.intcore.android.productsviewer.presenters.loginPresenter;

import com.intcore.android.productsviewer.data.user.User;

public interface LoginView {
    void showError(String errorMessage);
    void finishedLoading(User user);
}
