package com.intcore.android.productsviewer.presenters.favouriteListPresenter;

import com.intcore.android.productsviewer.MyApp;
import com.intcore.android.productsviewer.data.CategoryData.Data;
import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.intcore.android.productsviewer.data.Image;
import com.intcore.android.productsviewer.data.Product;
import com.intcore.android.productsviewer.database.MyDatabase;
import com.intcore.android.productsviewer.networking.favourite.FavouriteListNetworking;

import java.util.ArrayList;
import java.util.List;

public class FavouriteListPresenter implements FavouriteListNetworking.OnLoadFavouriteList, MyDatabase.IDatabaseFavourite {
    private FavouriteListNetworking mNetworking;
    private FavouriteListView mView;
    private MyDatabase mDatabase;

    public FavouriteListPresenter(FavouriteListView view) {
        mView = view;
        mNetworking = new FavouriteListNetworking(this);
        mDatabase = MyApp.getInstance().getMyDatabase();
    }

    public void startFetching(String apiToken){
        mNetworking.getFavouriteList(apiToken);
    }

    @Override
    public void onListLoad(Products products) {
        mView.onLoadFinish(products);
    }

    @Override
    public void onError(String message) {
        mView.onError(message);
    }

    @Override
    public void getAllFavProducts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Data> productsList = new ArrayList<>();
                List<com.intcore.android.productsviewer.database.Product> databaseProductsList = mDatabase.productDao().getAllFavProducts();
                int i =0;
                for (com.intcore.android.productsviewer.database.Product databaseProdct : databaseProductsList){
                    List<Image> images= new ArrayList<Image>();
                    images.add(new Image(i, databaseProdct.imageUri));
                    int price = databaseProdct.price;
                    Data p =new Data();
                    p.setNameEn(databaseProdct.englishName);
                    p.setPrice(databaseProdct.price);
                    p.setImages(images);
                    p.setId(Integer.parseInt(databaseProdct.id));
                    p.setIsFav(databaseProdct.isFav);
                    productsList.add(p);
                    i++;
                }
                Products products = new Products();
                products.setData(productsList);
                mView.onLoadFinish(products);
            }
        }).start();
    }
}
