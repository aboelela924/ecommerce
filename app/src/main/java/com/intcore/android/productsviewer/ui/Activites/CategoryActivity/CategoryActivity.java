package com.intcore.android.productsviewer.ui.Activites.CategoryActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.intcore.android.productsviewer.presenters.CategoryPresenter.CategoryPresenterView;
import com.intcore.android.productsviewer.presenters.CategoryPresenter.CategoryProductsPresenter;
import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.Category;
import com.intcore.android.productsviewer.data.CategoryData.Data;
import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryPresenterView {

    private static final String CATEGORY = "CATEGORY";
    private final static String TAG = "CategoryActivity";
    private Category mCategory;
    private ImageView mCategoryImageView;
    private Adapter mAdapter;
    private List<Data> mProductsList = new ArrayList<>();
    private CategoryProductsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent i = getIntent();
        if(i != null){
            mCategory = i.getParcelableExtra(CATEGORY);
        }

        mCategoryImageView = findViewById(R.id.activity_category_toolbar_image);
        Picasso.get()
                .load("https://e-commerce-dev.intcore.net/"+mCategory
                        .getImage()).into(mCategoryImageView);
        RecyclerView recyclerView = findViewById(R.id.activity_category_recycler_view_products);

        final GridLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this,
                2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter(mProductsList,CategoryActivity.this);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {

                    mPresenter.startFetching(mCategory.getId());

                }
            }
        });

        CollapsingToolbarLayout layout = findViewById(R.id.activity_category_collapsing_toolbar);
        layout.setTitle(mCategory.getName_en());

        mPresenter = new CategoryProductsPresenter(this);
        mPresenter.startFetching(mCategory.getId());

    }

    public static Intent createIntent(Context context, Category category){
        Intent i = new Intent(context, CategoryActivity.class);
        i.putExtra(CATEGORY, category);
        return i;
    }

    @Override
    public void setCategoriesAdapterData(Products categoryProducts) {
        mProductsList.addAll(categoryProducts.getData());
        mAdapter.notifyDataSetChanged();
    }
}
