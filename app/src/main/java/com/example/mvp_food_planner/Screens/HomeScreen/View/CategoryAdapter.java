package com.example.mvp_food_planner.Screens.HomeScreen.View;

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
import com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View.ByCategoryAdapter;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final Context context;
    private final List<CategoryFilter> categories;
    private final ByCategoryAdapter.CategoryClickListener listener; // Change here

    public CategoryAdapter(Context context, List<CategoryFilter> categories, ByCategoryAdapter.CategoryClickListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener; // Initialize the listener
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryFilter category = categories.get(position);
        holder.txtCategory.setText(category.getStrCategory());
        Glide.with(holder.itemView.getContext()).load(category.getStrCategoryThumb()).into(holder.imgMeal);

        // Set the click listener for the item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) { // Check if listener is not null
                listener.onCategoryClick(category.getStrCategory()); // Trigger the click event
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCategory;
        public ImageView imgMeal;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            imgMeal = itemView.findViewById(R.id.imgMeal);
        }
    }
}
