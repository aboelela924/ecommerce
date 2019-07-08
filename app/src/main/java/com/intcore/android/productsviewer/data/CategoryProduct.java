package com.intcore.android.productsviewer.data;

import com.intcore.android.productsviewer.data.CategoryData.Products;

public class CategoryProduct {
    private Products products;

    public CategoryProduct(Products products) {
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
