package com.example.test3.BaseClasses;

/**
 * Created by Денис on 25.07.2017.
 */

public class Category {

    private int  categoryId;
    private String title;
    private String imageUrl;
    private int hasSubcategories;
    private String fullName;
    private String categoryDescription;

    public Category(int categoryId, String title, String imageUrl, int hasSubcategories, String fullName, String categoryDescription) {
        this.categoryId = categoryId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.hasSubcategories = hasSubcategories;
        this.fullName = fullName;
        this.categoryDescription = categoryDescription;
    }

    public Category(){}

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getHasSubcategories() {
        return hasSubcategories;
    }

    public void setHasSubcategories(int hasSubcategories) {
        this.hasSubcategories = hasSubcategories;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
