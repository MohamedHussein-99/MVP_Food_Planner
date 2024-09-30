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

    public ByCategoryAdapter(Context context, List<CategoryFilter> categories) {
        this.context = context;
        this.categories = categories;
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
        Glide.with(holder.itemView.getContext())
                .load(category.getStrCategoryThumb())
                .into(holder.imgMeal);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategory;
        ImageView imgMeal;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            imgMeal = itemView.findViewById(R.id.imgMeal);
        }
    }
}

