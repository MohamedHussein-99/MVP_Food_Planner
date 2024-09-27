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
import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.R;

import java.util.List;

public class RandomMealAdapter extends RecyclerView.Adapter<RandomMealAdapter.ViewHolder> {


    private List<Meal> meals ;
    private Context context;
    private MealClickListener mealClickListener;

    public RandomMealAdapter(Context context, List<Meal> meals, MealClickListener mealClickListener) {
        this.meals = meals;
        this.context = context;
        this.mealClickListener = mealClickListener;
    }

    @NonNull
    @Override
    public RandomMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_meal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomMealAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.txtTitle.setText(meal.getStrMeal());
        holder.txtBrief.setText(meal.getStrCategory());
        Glide.with(context).load(meal.getStrMealThumb()).placeholder(R.drawable.ic_launcher_foreground).into(holder.imgMeal);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealClickListener.onMealClicked(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle ;
        TextView txtBrief;
        ImageView imgMeal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBrief = itemView.findViewById(R.id.txtBreif);
            imgMeal = itemView.findViewById(R.id.imgMeal);
        }
    }

    public interface MealClickListener {
        void onMealClicked(Meal meal);
    }
}
