package com.intcore.android.productsviewer.presenters.CategoryPresenter;

import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.intcore.android.productsviewer.networking.category.NetworkingCategory;

public class CategoryProductsPresenter implements NetworkingCategory.OnDataLoad {
    private CategoryPresenterView mPresenterView;
    private NetworkingCategory mNetworkingCategory;
    private int pageNumber = 1;
    private int maxPage = 10;
    public CategoryProductsPresenter(CategoryPresenterView category){
        mPresenterView = category;
        mNetworkingCategory = new NetworkingCategory(this);
    }

    public void startFetching(String categoryId){
        if(maxPage >= pageNumber) {
            mNetworkingCategory.loadCategoryproducts(categoryId, pageNumber);
        }
    }

    @Override
    public void dataFetched(Products products) {
        mPresenterView.setCategoriesAdapterData(products);
        maxPage = products.getLastPage();
        pageNumber++;

    }

    @Override
    public void onError(String message) {

    }
}
