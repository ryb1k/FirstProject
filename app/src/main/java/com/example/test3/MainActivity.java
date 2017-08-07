package com.example.test3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity { // TODO: 02.08.2017 make 2 activities or 2 fragments

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CategoryListAdapter categoryListAdapter;
    private List<Category> dataList;
    private List<Product> dataList1;
    private ProductListAdapter productListAdapter;
    CategoryApi categoryApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            categoryApi = new CategoryApi(MainActivity.this);
            categoryApi.userLogin();
        } catch (Exception e) {
            System.out.println(e);
        }
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        dataList = new ArrayList<>();
    }


    public void onDownloadSuccessCategory(List<Category> categoryList) {
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        CategoryApi categoryApi = new CategoryApi(MainActivity.this);
        categoryListAdapter = new CategoryListAdapter(MainActivity.this, categoryList, categoryApi);
        recyclerView.setAdapter(categoryListAdapter);
    }

    public void onDownloadSuccessProduct(List<Product> productList) {
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        ProductApi productApi = new ProductApi(MainActivity.this);
        productListAdapter = new ProductListAdapter(MainActivity.this, productList, productApi);
        recyclerView.setAdapter(productListAdapter);
        setTitle(dataList.get(0).getTitle());
    }
}
