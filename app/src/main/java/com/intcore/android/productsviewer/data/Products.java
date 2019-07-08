package com.intcore.android.productsviewer.data;

import java.util.ArrayList;
import java.util.List;

public class Products {
    List<Product> new_arrival = new ArrayList<>();
    List<Product> best_seller = new ArrayList<>();
    List<Category> top_categories = new ArrayList<>();


    public Products(List<Product> new_arrival, List<Product> best_seller, List<Category> top_categories) {
        this.new_arrival = new_arrival;
        this.best_seller = best_seller;
        this.top_categories = top_categories;
    }

    public List<Product> getNew_arrival() {
        return new_arrival;
    }

    public void setNew_arrival(List<Product> new_arrival) {
        this.new_arrival = new_arrival;
    }

    public List<Product> getBest_seller() {
        return best_seller;
    }

    public void setBest_seller(List<Product> best_seller) {
        this.best_seller = best_seller;
    }

    public List<Category> getTop_categories() {
        return top_categories;
    }

    public void setTop_categories(List<Category> top_categories) {
        this.top_categories = top_categories;
    }

}
