package com.example.test3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Денис on 26.07.2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private MainActivity context;
    private List<Product> products;
    private ProductApi request;

    public ProductListAdapter(MainActivity context, List<Product> products, ProductApi request) {
        this.context = context;
        this.products = products;
        this.request = request;
    }


    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ProductListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductListAdapter.ViewHolder holder, int position) {

        String title = products.get(position).getTitle();
        Number price = products.get(position).getPrice();
        holder.title.setText(title + " Цена: " + price + " руб.");
        Glide.with(context).load(products.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {

        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageButton image;
        public RecyclerView recyclerView;

        public ViewHolder(final View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageButton)itemView.findViewById(R.id.image);
        }
    }
}
