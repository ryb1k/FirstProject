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
 * Created by Денис on 25.07.2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{ // TODO: 02.08.2017 update formatting and rename to CategoryAdapter

    private MainActivity context;
    private List<MyData> myData;
    private CategoryRequest request;

    public CustomAdapter(MainActivity context, List<MyData> myData, CategoryRequest request) {
        this.context = context;
        this.myData = myData;
        this.request = request;
    }

    /*public CustomAdapter(MainActivity mainActivity, List<MyData> dataList) {
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(myData.get(position).getTitle());
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
        public CustomAdapter adapter;



        public ViewHolder(final View itemView, final CustomAdapter adapter) {
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
                        ProductRequest productRequest = new ProductRequest(context);
                        productRequest.userLogin();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println("onclick");
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
