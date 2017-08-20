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
    int categoryId;
    int offset = 0;
    public List<Product> productsDownloaded;
    private boolean isLoading = true;
    int pastVisibleItems, visibleItemCount, totalItemCount;
    GridLayoutManager gridLayoutManager = null;


    ProductApi productApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        categoryId = getIntent().getIntExtra("categoryId", 0);
        title = getIntent().getStringExtra("categoryTitle");
        productApi = new ProductApi();
        productApi.loadProducts(categoryId, offset, new ProductApiListener() {
            @Override
            public void onProductsLoaded(List<Product> products) {
                productsDownloaded = products;
                gridLayoutManager = new GridLayoutManager(ProductActivity.this, 1);
                recyclerView.setLayoutManager(gridLayoutManager);
                productListAdapter = new ProductListAdapter(ProductActivity.this, productsDownloaded); // TODO: 17.08.2017 pass this.products
                recyclerView.setAdapter(productListAdapter);
                setTitle(title);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (isLoading) {
                            if (dy > 0) {
                                visibleItemCount = gridLayoutManager.getChildCount();
                                totalItemCount = gridLayoutManager.getItemCount();
                                pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();
                                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                                    isLoading = false;
                                    LoadNextProductPart();
                                }
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(String error) {

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    public void LoadNextProductPart() {
        productApi.loadProducts(categoryId, offset + 15, new ProductApiListener() {
            @Override
            public void onProductsLoaded(List<Product> products) {
                for (int productIndex = 0; productIndex < products.size(); productIndex++) {
                    productsDownloaded.add(products.get(productIndex));
                }
                productListAdapter.notifyDataSetChanged();
                offset += 15;
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    public boolean isLoaded(List<Product> products) {
        if (products.size() == 0) {
            return true;
        } else
            return false;
    }

    // TODO: 17.08.2017 add method loadNextProductPart

    // TODO: 17.08.2017 subscribe to recyclerview scroll via ScrollViewListener and call loadNextProductPart

    // TODO: 17.08.2017 control multiple loadNextProductPart calling isLoading

    // TODO: 17.08.2017 use isLoaded


}
