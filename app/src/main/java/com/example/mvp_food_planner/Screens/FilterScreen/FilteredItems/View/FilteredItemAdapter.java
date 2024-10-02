package com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View.ByCategoryAdapter;

import java.util.List;

public class FilteredItemAdapter extends RecyclerView.Adapter<ByCategoryAdapter.CategoryViewHolder> {

    private final Context context;
    private final List<Meal> meals;
    private final MealClickListener listener;

    public FilteredItemAdapter(Context context, List<Meal> meal, MealClickListener listener) {
        this.context = context;
        this.meals = meal;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ByCategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_filter, parent, false);
        return new ByCategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ByCategoryAdapter.CategoryViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.title.setText(meal.getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(meal.strMealThumb)
                .into(holder.thumbnail);
        holder.itemView.setOnClickListener(v -> listener.onMealClicked(meal));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView thumbnail;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
    public interface MealClickListener {
        void onMealClicked(Meal meal);
    }
}
