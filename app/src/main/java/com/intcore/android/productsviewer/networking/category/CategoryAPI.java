package com.intcore.android.productsviewer.networking.category;

import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.intcore.android.productsviewer.data.CategoryProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryAPI {
    @GET("api/v1/user/app/products")
    Call<CategoryProduct> getCategoryProducts(@Query("category_id") String categoryId,
                                              @Query("page") int page);
}
