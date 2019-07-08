package com.intcore.android.productsviewer.ui.Activites.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.intcore.android.productsviewer.MyApp;
import com.intcore.android.productsviewer.data.user.User;
import com.intcore.android.productsviewer.database.MyDatabase;
import com.intcore.android.productsviewer.presenters.mainPresenter.Presenter;
import com.intcore.android.productsviewer.presenters.mainPresenter.PresenterView;
import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.Category;
import com.intcore.android.productsviewer.data.Product;
import com.intcore.android.productsviewer.ui.Activites.CategoryActivity.CategoryActivity;
import com.intcore.android.productsviewer.ui.Activites.favouriteActivity.FavouriteActivity;
import com.intcore.android.productsviewer.ui.Activites.loginActivity.LoginActivity;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PresenterView {
    private static final String USER = "USER";


    private AVLoadingIndicatorView mSpinner;
    private MainActivityAdapter mBestSellerAdapter;
    private MainActivityAdapter mNewArrivalAdapter;
    private CateogriesAdapter mTopCategoriesAdapter;
    private List<Product> mBestSellerProducts = new ArrayList<Product>();
    private List<Product> mNewArrivalProducts = new ArrayList<Product>();
    private List<Category> mTopCategories = new ArrayList<>();
    private BottomNavigationView  mBottomNavigationView;
    private Presenter mPresenter;
    private DrawerLayout mDrawerLayout;
    private User mUser;
    private Menu mMenu;
    private MyDatabase mMyDatabase;
    private Picasso mPicassoCache;

    public static Intent newIntent(Context context, User user){
        Intent i = new Intent(context, MainActivity.class);
        i.putExtra(USER, user);
        return i;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        mNewArrivalAdapter = new MainActivityAdapter(MainActivity.this, mNewArrivalProducts);
        mBestSellerAdapter = new MainActivityAdapter(MainActivity.this,mBestSellerProducts);
        if(i != null){
            mUser = i.getParcelableExtra(USER);
            mNewArrivalAdapter.setUser(mUser);
            mBestSellerAdapter.setUser(mUser);
        }

        MyApp myApp = MyApp.getInstance();

        mPicassoCache = MyApp.getInstance().getPicasso();
        mMyDatabase = MyApp.getInstance().getMyDatabase();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_grey_24dp);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View view = navigationView.getHeaderView(0);
        final TextView userNameTextView = view.findViewById(R.id.user_name_text_view);
        final ImageView coverImageView = view.findViewById(R.id.cover_image_view);
        final ImageView profileImageVeiw = view.findViewById(R.id.profile_image_view);
        mBottomNavigationView = findViewById(R.id.navigation);

        mMenu = navigationView.getMenu();
        if(mUser != null){
            userNameTextView.setText(mUser.getName());
            mPicassoCache.load("https://e-commerce-dev.intcore.net/"+ mUser.getCover())
                    .into(coverImageView);
            mPicassoCache.load("https://e-commerce-dev.intcore.net/"+mUser.getImage())
                    .into(profileImageVeiw);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    com.intcore.android.productsviewer.database.User databaseUser
                            = new com.intcore.android.productsviewer.database.User();
                    databaseUser.setId(mUser.getId());
                    databaseUser.setName(mUser.getName());
                    databaseUser.setProfileImage(mUser.getImage());
                    databaseUser.setCoverImage(mUser.getCover());
                    mMyDatabase.userDao().addUser(databaseUser);
                }
            }).start();



            mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.favorite:
                            Intent i = FavouriteActivity.newIntent(MainActivity.this, mUser.getApiToken());
                            startActivity(i);
                            return true;
                    }
                    return false;
                }
            });


        }else{
            new AsyncTask<Void, Void, Void>(){
                com.intcore.android.productsviewer.database.User user;
                @Override
                protected Void doInBackground(Void... voids) {
                    user = mMyDatabase.userDao().getUser();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    if(user == null) return;
                    userNameTextView.setText(user.getName());
                    mPicassoCache.load("https://e-commerce-dev.intcore.net/"+ user.getCoverImage())
                            .into(coverImageView);
                    mPicassoCache.load("https://e-commerce-dev.intcore.net/"+user.getProfileImage())
                            .into(profileImageVeiw);
                }
            }.execute();
            mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.favorite:
                            Intent i = FavouriteActivity.newIntent(MainActivity.this);
                            startActivity(i);
                            return true;
                    }
                    return false;
                }
            });
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        int index = mTopCategories.indexOf(new Category("",
                                "",
                                String.valueOf(menuItem.getItemId()) ));
                        Intent i = CategoryActivity.createIntent(MainActivity.this, mTopCategories.get(index));
                        startActivity(i);
                        return true;
                    }
                });

        RecyclerView newArrivalRecyclerView = findViewById(R.id.main_activity_recycler_view_new_arrival);
        newArrivalRecyclerView.setLayoutManager(new LinearLayoutManager(
                MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false
        ));

        newArrivalRecyclerView.setAdapter(mNewArrivalAdapter);


        RecyclerView bestSellerRecyclerView = findViewById(R.id.main_activity_recycler_view_best_seller);
        bestSellerRecyclerView.setLayoutManager(new LinearLayoutManager(
                MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false
        ));

        bestSellerRecyclerView.setAdapter(mBestSellerAdapter);

        RecyclerView topCategoriesRecyclerViewer = findViewById(R.id.main_activity_recycler_view_top_cateogries);
        topCategoriesRecyclerViewer.setLayoutManager(new LinearLayoutManager(
                MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false
        ));
        mTopCategoriesAdapter = new CateogriesAdapter(mTopCategories, MainActivity.this);
        topCategoriesRecyclerViewer.setAdapter(mTopCategoriesAdapter);

        mSpinner = findViewById(R.id.main_activity_spinner);

        mPresenter = new Presenter(this);
        if(mUser != null){

            mPresenter.startFetching(mUser.getApiToken());
        }else{
            mPresenter.getAllProductsOFType(com.intcore.android.productsviewer.database.Product.NEW_ARRIVAL);
            mPresenter.getAllProductsOFType(com.intcore.android.productsviewer.database.Product.BEST_SELLER);
            mPresenter.getAllCategories();
        }


    }

    @Override
    public void showSpinner() {
        mSpinner.setVisibility(View.VISIBLE);
        mSpinner.show();
    }

    @Override
    public void hideSpinner() {
        mSpinner.setVisibility(View.GONE);
        mSpinner.hide();
    }

    @Override
    public void setBestSellerAdapterData(List<Product> bestSeller) {
        mBestSellerProducts.addAll(bestSeller);
        if(mUser != null){
            for (Product product : bestSeller){
                mPresenter.insertInProduct(product,
                        com.intcore.android.productsviewer.database.Product.BEST_SELLER);
            }
        }
        mBestSellerAdapter.notifyDataSetChanged();

    }

    @Override
    public void setNewArrivalAdapterData(List<Product> newArrival) {
        mNewArrivalProducts.addAll(newArrival);
        if(mUser != null){
            for (Product product : newArrival){
                mPresenter.insertInProduct(product,
                        com.intcore.android.productsviewer.database.Product.NEW_ARRIVAL);
            }
        }
        mNewArrivalAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCategoriesAdapterData(List<Category> categories) {
        mTopCategories.addAll(categories);
        mTopCategoriesAdapter.notifyDataSetChanged();
        int i = 0;

        for (Category category: categories) {
            i++;
            if(mUser != null){
                mPresenter.insertInCategory(category);
            }
            mMenu.add(0,Integer.parseInt(category.getId()),i,category.getName_en());

        }

        mDrawerLayout.invalidate();
    }


    @Override
    public void showToastErrorMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
