package com.intcore.android.productsviewer.data;

import com.intcore.android.productsviewer.data.CategoryData.SubCategory;

import java.util.List;

public class Product {
    String name_en;
    String price;
    List<Image> images;
    String id;
    boolean is_fav;
    SubCategory subcategory;

    public Product(String name_en, String price, List<Image> images, String id, boolean is_fav, SubCategory subcategory) {
        this.name_en = name_en;
        this.price = price;
        this.images = images;
        this.id = id;
        this.is_fav = is_fav;
        this.subcategory = subcategory;
    }

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isIs_fav() {
        return is_fav;
    }

    public void setIs_fav(boolean is_fav) {
        this.is_fav = is_fav;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
