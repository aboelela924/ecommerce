package com.intcore.android.productsviewer.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("select * from product")
    List<Product> getAllProducts();

    @Query("select * from product where special_type like (:type)")
    List<Product> getAllProductsOfType(int type);

    @Query("select * from product where category_id like :id")
    List<Product> getAllProductsOfCategory(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProduct(Product product);

    @Query("Select * from product where is_fav == 1")
    List<Product> getAllFavProducts();

    @Query("update product set is_fav = case when is_fav = 1 then 0 else 1 end")
    void updaeFavourite();
}
