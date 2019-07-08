package com.intcore.android.productsviewer.presenters.favouriteListPresenter;

import com.intcore.android.productsviewer.data.CategoryData.Products;

public interface FavouriteListView {
    void onLoadFinish(Products products);
    void onError(String message);
}
