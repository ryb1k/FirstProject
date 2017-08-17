package com.example.test3;

import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

interface ProductApiListener {
    void onProductsLoaded(List<Product> products);
    void onFailure(String error);
}
