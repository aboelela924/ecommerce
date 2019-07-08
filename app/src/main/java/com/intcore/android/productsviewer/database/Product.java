package com.intcore.android.productsviewer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "product"/*,
            foreignKeys = @ForeignKey(entity = Category.class,
                                        parentColumns = "id",
                                        childColumns = "category_id")*/)
public class Product {

    public static final int NEW_ARRIVAL = 1;
    public static final int BEST_SELLER = 2;

    @ColumnInfo
    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "english_name")
    @NonNull
    public String englishName;

    @ColumnInfo(name = "category_id")
    public int CategoryId;

    @ColumnInfo
    public int price;


    @ColumnInfo(name = "image_uri")
    public String imageUri;

    @ColumnInfo(name = "is_fav")
    public boolean isFav;


    @ColumnInfo(name = "special_type")
    public int specialType;

}
