package com.intcore.android.productsviewer.data.CategoryData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.intcore.android.productsviewer.data.Image;

import java.util.List;

public class Data {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_ar")
    @Expose
    private String nameAr;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("description_ar")
    @Expose
    private String descriptionAr;
    @SerializedName("description_en")
    @Expose
    private String descriptionEn;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("shipping_specification")
    @Expose
    private String shippingSpecification;
    @SerializedName("sub_category_id")
    @Expose
    private Integer subCategoryId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("long_description_ar")
    @Expose
    private String longDescriptionAr;
    @SerializedName("long_description_en")
    @Expose
    private String longDescriptionEn;
    @SerializedName("brand_id")
    @Expose
    private Object brandId;
    @SerializedName("disable")
    @Expose
    private Integer disable;
    @SerializedName("friendly_url")
    @Expose
    private String friendlyUrl;
    @SerializedName("count_paid")
    @Expose
    private Integer countPaid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("today_offer")
    @Expose
    private Object todayOffer;
    @SerializedName("default_image")
    @Expose
    private String defaultImage;
    @SerializedName("is_fav")
    @Expose
    private Boolean isFav;
    @SerializedName("total_rate")
    @Expose
    private Integer totalRate;
    @SerializedName("is_reviewed")
    @Expose
    private Boolean isReviewed;
    @SerializedName("reviews")
    @Expose
    private List<Object> reviews = null;
    @SerializedName("colors")
    @Expose
    private List<Object> colors = null;
    @SerializedName("sizes")
    @Expose
    private List<Object> sizes = null;
    @SerializedName("images")
    @Expose
    private List<com.intcore.android.productsviewer.data.Image> images = null;
    @SerializedName("subcategory")
    @Expose
    private SubCategory subcategory;
    @SerializedName("brand")
    @Expose
    private Object brand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShippingSpecification() {
        return shippingSpecification;
    }

    public void setShippingSpecification(String shippingSpecification) {
        this.shippingSpecification = shippingSpecification;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLongDescriptionAr() {
        return longDescriptionAr;
    }

    public void setLongDescriptionAr(String longDescriptionAr) {
        this.longDescriptionAr = longDescriptionAr;
    }

    public String getLongDescriptionEn() {
        return longDescriptionEn;
    }

    public void setLongDescriptionEn(String longDescriptionEn) {
        this.longDescriptionEn = longDescriptionEn;
    }

    public Object getBrandId() {
        return brandId;
    }

    public void setBrandId(Object brandId) {
        this.brandId = brandId;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public String getFriendlyUrl() {
        return friendlyUrl;
    }

    public void setFriendlyUrl(String friendlyUrl) {
        this.friendlyUrl = friendlyUrl;
    }

    public Integer getCountPaid() {
        return countPaid;
    }

    public void setCountPaid(Integer countPaid) {
        this.countPaid = countPaid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getTodayOffer() {
        return todayOffer;
    }

    public void setTodayOffer(Object todayOffer) {
        this.todayOffer = todayOffer;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
        this.isFav = isFav;
    }

    public Integer getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Integer totalRate) {
        this.totalRate = totalRate;
    }

    public Boolean getIsReviewed() {
        return isReviewed;
    }

    public void setIsReviewed(Boolean isReviewed) {
        this.isReviewed = isReviewed;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public void setReviews(List<Object> reviews) {
        this.reviews = reviews;
    }

    public List<Object> getColors() {
        return colors;
    }

    public void setColors(List<Object> colors) {
        this.colors = colors;
    }

    public List<Object> getSizes() {
        return sizes;
    }

    public void setSizes(List<Object> sizes) {
        this.sizes = sizes;
    }

    public List<com.intcore.android.productsviewer.data.Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public Object getBrand() {
        return brand;
    }

    public void setBrand(Object brand) {
        this.brand = brand;
    }
}
