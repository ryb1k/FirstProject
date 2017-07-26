package com.example.test3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Денис on 26.07.2017.
 */

public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.ViewHolder> {

    private Context context;
    private List<MyData1> myData;
    private MainActivity.ProductRequest request;

    public CustomAdapter1(Context context, List<MyData1> myData, MainActivity.ProductRequest request) {
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
    public void onBindViewHolder(CustomAdapter1.ViewHolder holder, int position) {

        holder.title.setText(myData.get(position).getTitle()+" Цена: "+myData.get(position).getPrice()+" руб.");
        Glide.with(context).load(myData.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {

        return myData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageButton image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageButton)itemView.findViewById(R.id.image);
        }
    }
}
