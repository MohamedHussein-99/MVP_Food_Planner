package com.example.mvp_food_planner.Screens.FilterScreen.ByIngredients.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvp_food_planner.Model.POJO.IngredientFilter;

import java.util.List;
import com.example.mvp_food_planner.R;
public class ByIngredientAdapter extends RecyclerView.Adapter<ByIngredientAdapter.IngredientViewHolder> {

    private final Context context;
    private final List<IngredientFilter> ingredients;
    private final IngredientClickListener listener; // Add this line

    // Create a listener interface
    public interface IngredientClickListener {
        void onIngredientClicked(IngredientFilter ingredient);
    }

    public ByIngredientAdapter(Context context, List<IngredientFilter> ingredients, IngredientClickListener listener) {
        this.context = context;
        this.ingredients = ingredients;
        this.listener = listener; // Initialize the listener
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_ingred, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        IngredientFilter ingredient = ingredients.get(position);
        holder.txtCategory.setText(ingredient.getStrIngredient());

        // Construct the image URL
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getStrIngredient() + ".png";

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.imgMeal);

        // Set an OnClickListener to the item view
        holder.itemView.setOnClickListener(v -> listener.onIngredientClicked(ingredient)); // Pass the clicked ingredient
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategory;
        ImageView imgMeal;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            imgMeal = itemView.findViewById(R.id.imgMeal);
        }
    }
}


