package com.intcore.android.productsviewer.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Category  implements Parcelable {
    String image;
    String name_en;
    String id;

    @Override
    public boolean equals( Object obj) {
        return ((Category)obj).id.equals(this.id) ;
    }

    public Category(String image, String name_en, String id) {
        this.image = image;
        this.name_en = name_en;
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.image);
        parcel.writeString(this.name_en);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Category createFromParcel(Parcel in){
            return  new Category(in);
        }

        @Override
        public Object[] newArray(int i) {
            return new Category[0];
        }
    };

    public Category(Parcel in){
        this.id = in.readString();
        this.image = in.readString();
        this.name_en = in.readString();
    }
}
