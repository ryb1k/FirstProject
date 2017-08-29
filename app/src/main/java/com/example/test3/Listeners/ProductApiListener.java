package com.example.test3.Listeners;

import com.example.test3.BaseClasses.Product;

import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public interface ProductApiListener {
    void onProductsLoaded(List<Product> products);
    void onFailure(String error);
}
