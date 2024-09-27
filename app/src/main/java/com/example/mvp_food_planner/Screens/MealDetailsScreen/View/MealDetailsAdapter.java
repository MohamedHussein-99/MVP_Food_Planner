package com.example.mvp_food_planner.Screens.MealDetailsScreen.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.mvp_food_planner.R;

import java.util.List;
import java.util.Map;

public class MealDetailsAdapter extends RecyclerView.Adapter<MealDetailsAdapter.ViewHolder> {

    // List of ingredients in key-value pairs
    private List<Map.Entry<String, String>> ingredients;

    public MealDetailsAdapter(List<Map.Entry<String, String>> ingredients) {
        this.ingredients = ingredients;
    }

    public void setIngredients(List<Map.Entry<String, String>> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_meal_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealDetailsAdapter.ViewHolder holder, int position) {
        Map.Entry<String, String> ingredient = ingredients.get(position);
        String ingredientName = ingredient.getKey();
        String ingredientMeasure = ingredient.getValue();

        // Set the ingredient name and measure
        if (holder.txtmeasure != null) {
            holder.txtmeasure.setText(ingredientName + " - " + ingredientMeasure);
        }

        // Load the ingredient image using Glide
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientName + ".png";
        if (holder.imgingred != null) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imgingred);
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmeasure;
        ImageView imgingred;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmeasure = itemView.findViewById(R.id.txtIngrediant);
            imgingred = itemView.findViewById(R.id.imgingred);
        }
    }

}
