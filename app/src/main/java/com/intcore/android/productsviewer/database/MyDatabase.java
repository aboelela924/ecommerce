package com.intcore.android.productsviewer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import java.util.List;

@Database(entities = {Product.class, Category.class, User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public interface  IDatabase{
        void insertInProduct(com.intcore.android.productsviewer.data.Product product, int type);
        void insertInCategory(com.intcore.android.productsviewer.data.Category category);
        void getAllCategories();
        void deleteAllCategories();
        void getAllProductsOFType(int type);
        void getAllProductsOfCategory(String categoryId);
    }

    public interface IDatabaseFavourite{
        void getAllFavProducts();
    }

    public abstract CategoryDao categoryDao();
    public abstract ProductDao productDao();
    public abstract UserDao userDao();

    public final static String NAME = "MyDatabase";
}
