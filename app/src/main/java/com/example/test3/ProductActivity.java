package com.example.test3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductListAdapter productListAdapter;
    private String title;

    ProductApi productApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        int categoryId = getIntent().getIntExtra("categoryId", 0);
        title = getIntent().getStringExtra("categoryTitle");
        productApi = new ProductApi();
        productApi.loadProducts(categoryId, new ProductApiListener() {
            @Override
            public void onProductsLoaded(List<Product> products) {
                recyclerView.setLayoutManager(new GridLayoutManager(ProductActivity.this, 1));
                productListAdapter = new ProductListAdapter(ProductActivity.this, products); // TODO: 17.08.2017 pass this.products
                recyclerView.setAdapter(productListAdapter);

                setTitle(title);
            }

            @Override
            public void onFailure(String error) {

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    // TODO: 17.08.2017 add method loadNextProductPart

    // TODO: 17.08.2017 subscribe to recyclerview scroll via ScrollViewListener and call loadNextProductPart

    // TODO: 17.08.2017 control multiple loadNextProductPart calling isLoading

    // TODO: 17.08.2017 use isLoaded


}
