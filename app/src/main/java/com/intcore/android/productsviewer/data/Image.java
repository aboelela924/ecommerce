package com.intcore.android.productsviewer.data;

public class Image {
    int id;
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Image(int id, String image) {
        this.id = id;
        this.image = image;
    }
}
