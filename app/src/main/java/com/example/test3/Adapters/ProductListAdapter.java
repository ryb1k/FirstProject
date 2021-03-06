package com.example.test3.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test3.BaseClasses.Product;
import com.example.test3.CurrencyHelper;
import com.example.test3.Activities.ProductActivity;
import com.example.test3.R;

import java.util.List;

/**
 * Created by Денис on 26.07.2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private ProductActivity context;
    private List<Product> products;

    public ProductListAdapter(ProductActivity context, List<Product> products) {
        this.context = context;
        this.products = products;
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
        CurrencyHelper currencyHelper = new CurrencyHelper();
        holder.title.setText(currencyHelper.formatPrice(title, price));
        Glide.with(context).load(products.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageButton image;

        public ViewHolder(final View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageButton)itemView.findViewById(R.id.image);
        }
    }
}
