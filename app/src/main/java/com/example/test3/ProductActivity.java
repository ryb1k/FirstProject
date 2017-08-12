package com.example.test3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public class ProductActivity extends AppCompatActivity implements ProductApiListener {

    // TODO: 11.08.2017 распределить классы по Model, View, Activity
    private RecyclerView recyclerView;
    private ProductListAdapter productListAdapter;
    private String title;

    ProductApi productApi;

    @Override
    public void onProductsLoaded(List<Product> products) {
        recyclerView.setLayoutManager(new GridLayoutManager(ProductActivity.this,1));
        productListAdapter = new ProductListAdapter(ProductActivity.this, products);
        recyclerView.setAdapter(productListAdapter);

        setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        int categoryId = getIntent().getIntExtra("categoryId", 0);
        title = getIntent().getStringExtra("categoryTitle");
        try {
            productApi = new ProductApi();
            productApi.loadProducts(categoryId, this);
        } catch (Exception e) {
            System.out.println(e);
        }
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }


}
