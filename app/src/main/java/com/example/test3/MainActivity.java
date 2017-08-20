package com.example.test3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;



import java.util.List;

public class MainActivity extends AppCompatActivity implements  OnCategorySelectedListener {

    private RecyclerView recyclerView;
    private CategoryListAdapter categoryListAdapter;
    private List<Category> categories;
    CategoryApi categoryApi;
    ProductApi productApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryApi = new CategoryApi();
        categoryApi.loadCategories(new CategoryApiListener() {
            @Override
            public void onCategoryLoaded(List<Category> categoryList) {
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
                categoryListAdapter = new CategoryListAdapter(MainActivity.this, categoryList);
                recyclerView.setAdapter(categoryListAdapter);
            }

            @Override
            public void onFailure(String error) {
                System.out.println(error);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCategorySelected(new Category());
            }
        });

    }

    @Override
    public void onCategorySelected(Category category) {
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        int categoryId = category.getCategoryId();
        String title = category.getTitle();
        if (title == null) {
            title = "Продукты";
        }
        intent.putExtra("categoryId", categoryId);
        intent.putExtra("categoryTitle", title);
        startActivity(intent);
    }
}
