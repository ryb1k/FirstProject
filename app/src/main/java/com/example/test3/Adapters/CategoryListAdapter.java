package com.example.test3.Adapters;

        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.example.test3.BaseClasses.Category;
        import com.example.test3.Activities.MainActivity;
        import com.example.test3.R;

        import java.util.List;

/**
 * Created by Денис on 25.07.2017.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private MainActivity context;
    private List<Category> categories;

    public CategoryListAdapter(MainActivity context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(categories.get(position).getTitle());
        Glide.with(context).load(categories.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageButton image;
        public CategoryListAdapter adapter;

        public ViewHolder(final View itemView, final CategoryListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageButton) itemView.findViewById(R.id.image);
            image.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    int categoryIndex = getAdapterPosition();
                    context.onCategorySelected(categories.get(categoryIndex));
                }
            });
        }
    }
}
