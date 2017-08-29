package com.example.test3.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test3.Adapters.ProductListAdapter;
import com.example.test3.Api.ProductApi;
import com.example.test3.BaseClasses.Product;
import com.example.test3.Listeners.ProductApiListener;
import com.example.test3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 08.08.2017.
 */

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductListAdapter productListAdapter;
    private String title;
    int categoryId;
    public List<Product> productsDownloaded;
    private boolean isLoading = false;
    private boolean isAllLoaded = false;
    int pastVisibleItems, visibleItemCount, totalItemCount;
    GridLayoutManager gridLayoutManager = null;


    ProductApi productApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        categoryId = getIntent().getIntExtra("categoryId", 0);
        title = getIntent().getStringExtra("categoryTitle");
        productApi = new ProductApi();
        productsDownloaded = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(ProductActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        productListAdapter = new ProductListAdapter(ProductActivity.this, productsDownloaded);
        recyclerView.setAdapter(productListAdapter);
        setTitle(title);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loadNextProductPart();
                    }
                }
            }
        });
        loadNextProductPart();
    }

    public void loadNextProductPart() {
        if (isLoading || isAllLoaded) {
            return;
        }
        isLoading = true;
        productApi.loadProducts(categoryId, productsDownloaded.size(), new ProductApiListener() {
            @Override
            public void onProductsLoaded(List<Product> products) {
                if (products.size() == 0) {
                    isAllLoaded = true;
                }
                productsDownloaded.addAll(products);
                productListAdapter.notifyDataSetChanged();
                isLoading = false;
            }

            @Override
            public void onFailure(String error) {
                isLoading = false;
            }
        });
    }
}
