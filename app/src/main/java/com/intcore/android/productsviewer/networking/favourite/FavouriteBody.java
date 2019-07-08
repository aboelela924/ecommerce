package com.intcore.android.productsviewer.networking.favourite;

public class FavouriteBody {
    String api_token;
    String product_id;

    public FavouriteBody(String api_token, String product_id) {
        this.api_token = api_token;
        this.product_id = product_id;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
