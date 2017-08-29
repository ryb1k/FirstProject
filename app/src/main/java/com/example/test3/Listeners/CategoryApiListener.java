package com.example.test3.Listeners;

import com.example.test3.BaseClasses.Category;

import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public interface CategoryApiListener {
    void onCategoryLoaded(List<Category> categoryList);
    void onFailure(String error);
}