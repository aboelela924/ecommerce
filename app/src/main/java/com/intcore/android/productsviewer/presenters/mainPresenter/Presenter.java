package com.intcore.android.productsviewer.presenters.mainPresenter;

import com.intcore.android.productsviewer.MyApp;
import com.intcore.android.productsviewer.data.Image;
import com.intcore.android.productsviewer.data.Products;
import com.intcore.android.productsviewer.database.Category;
import com.intcore.android.productsviewer.database.MyDatabase;
import com.intcore.android.productsviewer.database.Product;
import com.intcore.android.productsviewer.networking.main.Network;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements Network.OnDataLoad, MyDatabase.IDatabase {
    private PresenterView mView;
    private  Network mNetwork;
    private MyDatabase mMyDatabase;

    public Presenter(PresenterView view) {
        mView = view;
        mNetwork = new Network(this);
        mMyDatabase = MyApp.getInstance().getMyDatabase();
    }

    public void startFetching( String apiToken ){
        mNetwork.loadData(apiToken);
        mView.showSpinner();
    }
    public void startFetching(){
        mNetwork.loadData();
        mView.showSpinner();
    }

    @Override
    public void dataFetched(Products products) {
        mView.setCategoriesAdapterData(products.getTop_categories());
        mView.setNewArrivalAdapterData(products.getNew_arrival());
        mView.setBestSellerAdapterData(products.getBest_seller());
        mView.hideSpinner();
    }

    @Override
    public void onError(String message) {
        mView.showToastErrorMessage(message);
    }

    @Override
    public void insertInProduct(final com.intcore.android.productsviewer.data.Product product, int type) {
        final com.intcore.android.productsviewer.database.Product pro
                = new com.intcore.android.productsviewer.database.Product();
        pro.id = product.getId();
        pro.CategoryId = product.getSubcategory().getCategoryId();
        pro.englishName = product.getName_en();
        pro.imageUri = product.getImages().get(0).getImage();
        pro.isFav = product.isIs_fav();
        pro.specialType = type;
        pro.price = Integer.parseInt(product.getPrice());
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMyDatabase.productDao().addProduct(pro);
            }
        }).start();
    }

    @Override
    public void insertInCategory(com.intcore.android.productsviewer.data.Category category) {
        final com.intcore.android.productsviewer.database.Category cat
                = new com.intcore.android.productsviewer.database.Category();
        cat.id = category.getId();
        cat.imageUri = category.getImage();
        cat.nameEn = category.getName_en();
        new Thread(new Runnable() {
            @Override
            public void run() {

                mMyDatabase.categoryDao().addCategory(cat);
            }
        }).start();
    }

    @Override
    public void getAllCategories() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<com.intcore.android.productsviewer.data.Category> categories = new ArrayList<>();
                List<Category> databaseCategories = mMyDatabase.categoryDao().getAllTopCategory();
                for (Category base : databaseCategories){
                    com.intcore.android.productsviewer.data.Category cat =
                            new com.intcore.android.productsviewer.data.Category(base.imageUri, base.nameEn,base.id);
                    categories.add(cat);
                }
                mView.setCategoriesAdapterData(categories);
            }
        }).start();

    }

    @Override
    public void deleteAllCategories() {
        mMyDatabase.categoryDao().deleteAll();
    }

    @Override
    public void getAllProductsOFType(final int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<com.intcore.android.productsviewer.data.Product> productsList = new ArrayList<>();
                List<Product> databaseProductsList = mMyDatabase.productDao().getAllProductsOfType(type);
                int i =0;
                for (Product databaseProdct : databaseProductsList){
                    List<Image> images= new ArrayList<Image>();
                    images.add(new Image(i, databaseProdct.imageUri));
                    int price = databaseProdct.price;
                    com.intcore.android.productsviewer.data.Product p =
                            new com.intcore.android.productsviewer.data.Product(databaseProdct.englishName
                                    ,String.valueOf(databaseProdct.price),images ,
                                    databaseProdct.id,databaseProdct.isFav, null);
                    productsList.add(p);
                }
                i++;
                if(type == Product.NEW_ARRIVAL){
                    mView.setNewArrivalAdapterData(productsList);
                }else{
                    mView.setBestSellerAdapterData(productsList);
                }
            }
        }).start();

    }

    @Override
    public void getAllProductsOfCategory(final String categoryId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<com.intcore.android.productsviewer.data.Product> productsList = new ArrayList<>();
                List<Product> databaseProductsList = mMyDatabase.productDao().getAllProductsOfCategory(categoryId);
                int i =0;
                for (Product databaseProdct : databaseProductsList){
                    List<Image> images= new ArrayList<Image>();
                    images.add(new Image(i, databaseProdct.imageUri));
                    com.intcore.android.productsviewer.data.Product p =
                            new com.intcore.android.productsviewer.data.Product(databaseProdct.englishName
                                    ,String.valueOf(databaseProdct.price),images ,
                                    databaseProdct.id,databaseProdct.isFav, null);
                    productsList.add(p);
                }
                i++;
            }
        }).start();


    }
}
