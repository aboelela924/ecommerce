package com.intcore.android.productsviewer.ui.Activites.favouriteActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.CategoryData.Data;
import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.intcore.android.productsviewer.data.user.User;
import com.intcore.android.productsviewer.presenters.favouriteListPresenter.FavouriteListPresenter;
import com.intcore.android.productsviewer.presenters.favouriteListPresenter.FavouriteListView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity implements FavouriteListView {
    private static final String API_TOKEN = "API_TOKEN";

    private FavouriteAdapter mAdapter;
    private List<Data> mProducts = new ArrayList<>();
    private FavouriteListPresenter mPresenter;

    public static Intent newIntent(Context context, String apiToken){
        Intent i = new Intent(context, FavouriteActivity.class);
        i.putExtra(API_TOKEN, apiToken);
        return i;
    }

    public static Intent newIntent(Context context){
        Intent i = new Intent(context, FavouriteActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        RecyclerView favouriteRecyclerView = findViewById(R.id.favourite_items_recycler_view);
        favouriteRecyclerView.setLayoutManager(new LinearLayoutManager(FavouriteActivity.this));

        mAdapter = new FavouriteAdapter(mProducts, this);
        favouriteRecyclerView.setAdapter(mAdapter);

        mPresenter = new FavouriteListPresenter(this);
        Intent i = getIntent();
        if(i != null){
            String apiToken = i.getStringExtra(API_TOKEN);
            if(apiToken == null){
                mPresenter.getAllFavProducts();
            }else{
                mPresenter.startFetching(apiToken);
                mAdapter.setApiToken(apiToken);
            }

        }
    }

    @Override
    public void onLoadFinish(Products products) {
        mProducts.addAll(products.getData());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "An error occured: "+message, Toast.LENGTH_LONG).show();
    }
}
