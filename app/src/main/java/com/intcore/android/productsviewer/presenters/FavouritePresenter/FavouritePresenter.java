package com.intcore.android.productsviewer.presenters.FavouritePresenter;

import com.intcore.android.productsviewer.MyApp;
import com.intcore.android.productsviewer.data.Image;
import com.intcore.android.productsviewer.data.Message;
import com.intcore.android.productsviewer.data.Product;
import com.intcore.android.productsviewer.database.MyDatabase;
import com.intcore.android.productsviewer.networking.favourite.FavouriteBody;
import com.intcore.android.productsviewer.networking.favourite.FavouriteNetworking;

import java.util.ArrayList;
import java.util.List;

public class FavouritePresenter implements FavouriteNetworking.OnAddLikeLoad {
    private  FavouriteView mView;
    private FavouriteNetworking mNetworking;
    public FavouritePresenter(FavouriteView view) {
        mView = view;
        mNetworking = new FavouriteNetworking(this);

    }
    public void addToFavourite(FavouriteBody body){
        mNetworking.addToFavourite(body);
    }

    @Override
    public void onAddToFavouriteResponse(Message message) {
        mView.onAddToFavouriteFinished(message);
    }

    @Override
    public void onAddToFavouriteFail(String message) {
        mView.onAddToFavouriteFailed(message);
    }



}
