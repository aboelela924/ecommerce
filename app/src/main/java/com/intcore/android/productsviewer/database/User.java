package com.intcore.android.productsviewer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    int id;

    @ColumnInfo
    String name;

    @ColumnInfo(name = "cover_image")
    String coverImage;

    @ColumnInfo(name = "profile_image")
    String profileImage;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
