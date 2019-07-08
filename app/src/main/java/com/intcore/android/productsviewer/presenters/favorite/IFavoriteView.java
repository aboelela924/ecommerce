package com.intcore.android.productsviewer.presenters.favorite;

public interface IFavoriteView {

    void showLoading();
    void hideLoading();
    void showMessage(String msg);

    void onReceiveFavoriteList();
}
