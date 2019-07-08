package com.intcore.android.productsviewer.data.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.intcore.android.productsviewer.MyApp;
import com.intcore.android.productsviewer.data.user.User;

public class UserData {
    private static UserData ourInstance;
    private static final String API_TOKEN = "API_TOKEN";
    private static SharedPreferences mSharedPreferences;

    public static UserData getInstance() {
        if(ourInstance == null){
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance().getApplicationContext());
            ourInstance = new UserData();
        }
        return ourInstance;
    }


    public void setToken(String token){
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(API_TOKEN, token)
                .apply();
    }

    public String getApiToken(){
        String apiTokeen = mSharedPreferences.getString(API_TOKEN,"");
        return apiTokeen;
    }
}
