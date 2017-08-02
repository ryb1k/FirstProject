package com.example.test3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Денис on 26.07.2017.
 */

public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.ViewHolder> { // TODO: 02.08.2017 ProductListAdapter

    private MainActivity context;
    private List<MyData1> myData;
    private ProductRequest request;

    public CustomAdapter1(MainActivity context, List<MyData1> myData, ProductRequest request) {
        this.context = context;
        this.myData = myData;
        this.request = request;
    }


    @Override
    public CustomAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new CustomAdapter1.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CustomAdapter1.ViewHolder holder, int position) { // TODO: 02.08.2017 every single parse in one varibale

        holder.title.setText(myData.get(position).getTitle()+" Цена: "+myData.get(position).getPrice()+" руб."); // TODO: 02.08.2017 update formatting
        Glide.with(context).load(myData.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {

        return myData.size();
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
