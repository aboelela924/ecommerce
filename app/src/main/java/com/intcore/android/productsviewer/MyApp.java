package com.intcore.android.productsviewer;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.intcore.android.productsviewer.database.MyDatabase;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class MyApp extends Application {


    private static  MyApp app;
    private Picasso picassoWithCache;


    public static MyApp getInstance(){
        return app;
    }
    private MyDatabase mMyDatabase;
    @Override
    public void onCreate() {
        super.onCreate();

       /*public class RestClientApp extends Application {
            // when upgrading versions, kill the original tables by using fallbackToDestructiveMigration()

        }

        public MyDatabase getMyDatabase() {
            return myDatabase;
        }*/
        app = this;

       mMyDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "My-Database")
               .build();


    }

    public MyDatabase getMyDatabase() {
        return mMyDatabase;
    }
    public Picasso getPicasso(){
        if(picassoWithCache == null){
            getPicassoWithCache();
        }
        return picassoWithCache;
    }

    private   Picasso getPicassoWithCache(){
        File httpCacheDirectory = new File(getCacheDir(), "picasso-cache");
        Cache cache = new Cache(httpCacheDirectory, 15 * 1024 * 1024);
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().cache(cache);
        picassoWithCache = new Picasso.Builder(this).downloader(new OkHttp3Downloader(okHttpClientBuilder.build())).build();
        return picassoWithCache;
    }
}
