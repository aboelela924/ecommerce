package com.intcore.android.productsviewer.presenters.CategoryPresenter;

import com.intcore.android.productsviewer.data.CategoryData.Products;

public interface CategoryPresenterView {
    void setCategoriesAdapterData(Products categoryProducts);
}
