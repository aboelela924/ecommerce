package com.intcore.android.productsviewer.presenters.mainPresenter;

import com.intcore.android.productsviewer.data.Category;
import com.intcore.android.productsviewer.data.Product;

import java.util.List;

public interface PresenterView {
    void showSpinner();
    void hideSpinner();
    void setBestSellerAdapterData(List<Product> bestSeller);
    void setNewArrivalAdapterData(List<Product> newArrival);
    void setCategoriesAdapterData(List<Category> newArrival);
    void showToastErrorMessage(String message);
}
