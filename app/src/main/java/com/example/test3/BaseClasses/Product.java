package com.example.test3.BaseClasses;

/**
 * Created by Денис on 26.07.2017.
 */

public class Product {

    private int productId;
    private String title;
    private String productDescription;
    private Number price;
    private Number rating;
    private String imageUrl;
    private String[] images;

    public Product(int productId, String title, String productDescription, Number price, Number rating, String imageUrl, String[] images) {
        this.productId = productId;
        this.title = title;
        this.productDescription = productDescription;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.images = images;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getRating() {
        return rating;
    }

    public void setRating(Number rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}


