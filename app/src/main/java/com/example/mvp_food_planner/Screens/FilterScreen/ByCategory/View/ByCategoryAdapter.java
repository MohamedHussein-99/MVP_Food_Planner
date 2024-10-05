package com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.HomeScreen.View.CategoryAdapter;

import java.util.List;


public class ByCategoryAdapter extends RecyclerView.Adapter<ByCategoryAdapter.CategoryViewHolder> {

    private final Context context;
    private final List<CategoryFilter> categories;
    private final CategoryClickListener listener;

    public ByCategoryAdapter(Context context, List<CategoryFilter> categories, CategoryClickListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener; // Initialize listener
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_filter, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryFilter category = categories.get(position);
        holder.title.setText(category.getStrCategory());
        Glide.with(holder.itemView.getContext())
                .load(category.getStrCategoryThumb())
                .into(holder.thumbnail);

        // Handle item click
        holder.itemView.setOnClickListener(v -> listener.onCategoryClick(category.getStrCategory()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    // Listener interface for category clicks
    public interface CategoryClickListener {
        void onCategoryClick(String category);
    }
}



