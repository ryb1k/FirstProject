package com.example.test3;

import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public interface CategoryApiListener {
    void onCategoryLoaded(List<Category> categoryList);
}