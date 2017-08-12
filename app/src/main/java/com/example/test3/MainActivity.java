package com.example.test3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryApiListener, OnCategorySelectedListener {

    private RecyclerView recyclerView;
    private CategoryListAdapter categoryListAdapter;
    private List<Category> categories;
    CategoryApi categoryApi;

    @Override
    public void onCategoryLoaded(List<Category> categoryList) {
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        categoryListAdapter = new CategoryListAdapter(MainActivity.this, categoryList);
        recyclerView.setAdapter(categoryListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            categoryApi = new CategoryApi();
            categoryApi.loadCategories(this);
        } catch (Exception e) {
            System.out.println(e);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }


    @Override
    public void onCategorySelected(Category category) {
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        int categoryId = category.getCategoryId();
        String title = category.getTitle();
        intent.putExtra("categoryId", categoryId);
        intent.putExtra("categoryTitle", title);
        startActivity(intent);
    }
}
