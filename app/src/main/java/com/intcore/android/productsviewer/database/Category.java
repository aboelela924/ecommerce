package com.intcore.android.productsviewer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "category")
public class Category {

    @PrimaryKey
    @ColumnInfo
    @NonNull
    public String id;

    @ColumnInfo(name = "name_en")
    @NonNull
    public String nameEn;

    @ColumnInfo(name = "image_uri")
    @NonNull
    public String imageUri;


}
