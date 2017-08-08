package com.example.test3;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Денис on 25.07.2017.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private MainActivity context;
    private List<Category> category;
    private CategoryApi request;
    public int position;

    public CategoryListAdapter(MainActivity context, List<Category> category, CategoryApi request) {
        this.context = context;
        this.category = category;
        this.request = request;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.position=position;
        holder.title.setText(category.get(position).getTitle());
        Glide.with(context).load(category.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageButton image;
        public RecyclerView recyclerView;
        public CategoryListAdapter adapter;

        public ViewHolder(final View itemView, final CategoryListAdapter adapter) {
            super(itemView);
            this.adapter=adapter;
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageButton) itemView.findViewById(R.id.image);
            image.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){ // TODO: 02.08.2017 send category touch to activity via listener
                    recyclerView = (RecyclerView) adapter.context.findViewById(R.id.recyclerView);
                    recyclerView.setVisibility(View.INVISIBLE);
                    try {
                        //Intent intent = new Intent(MainActivity.this,ProductActivity.class);
                        ProductApi productApi = new ProductApi(context);
                        productApi.loadProducts();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
