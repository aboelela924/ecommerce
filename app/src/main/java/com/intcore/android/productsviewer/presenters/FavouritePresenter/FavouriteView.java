package com.intcore.android.productsviewer.presenters.FavouritePresenter;

import com.intcore.android.productsviewer.data.Message;

public interface FavouriteView {
    void onAddToFavouriteFinished(Message message);
    void onAddToFavouriteFailed(String errorMessage);

}
