package com.example.mvp_food_planner.Screens.PlannerScreen.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Entity.PlannedMeal;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.HomeScreen.View.RandomMealAdapter;

import java.util.List;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.PlanViewHolder> {
    private List<PlannedMeal> plannedMeals;
    private Context context;
    private PlannerListener listener;
    private RandomMealAdapter.MealClickListener mealClickListener;

    public PlannerAdapter(List<PlannedMeal> plannedMeals, Context context, PlannerListener listener, RandomMealAdapter.MealClickListener mealClickListener) {
        this.plannedMeals = plannedMeals;
        this.context = context;
        this.listener = listener;
        this.mealClickListener = mealClickListener;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_plan, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        PlannedMeal meal = plannedMeals.get(position);

        holder.txtPlanTitle.setText(meal.strMeal);
        holder.txtPlanCategory.setText(meal.strCategory);
        holder.txtPlanCountry.setText(meal.strArea);
        holder.txtPlanDate.setText(meal.getDate().toString());

        Glide.with(context).load(meal.strMealThumb).into(holder.imgPlan);

        // Remove meal on trash click
        holder.lottieTrash.setOnClickListener(v -> {
            listener.onDeleteMeal(meal);
        });
        // Add meal click listener
        holder.itemView.setOnClickListener(v -> {
            Meal mealData = new Meal(); // Convert  PlannedMeal to Meal
            mealData.idMeal = meal.idMeal;
            mealData.strMeal = meal.strMeal;
            mealClickListener.onMealClicked(mealData);
        });
    }

    @Override
    public int getItemCount() {
        return plannedMeals != null ? plannedMeals.size() : 0;
    }

    public void updatePlannedMeals(List<PlannedMeal> newPlannedMeals) {
        this.plannedMeals.clear();
        this.plannedMeals.addAll(newPlannedMeals);
        notifyDataSetChanged(); // Refresh RecyclerView
    }

    public class PlanViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPlan;
        TextView txtPlanTitle, txtPlanCategory, txtPlanCountry, txtPlanDate;
        LottieAnimationView lottieTrash;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPlan = itemView.findViewById(R.id.imgplan);
            txtPlanTitle = itemView.findViewById(R.id.txtPlanTitle);
            txtPlanCategory = itemView.findViewById(R.id.txtPlanCategory);
            txtPlanCountry = itemView.findViewById(R.id.txtPlanCountry);
            txtPlanDate = itemView.findViewById(R.id.txtPlanDate);
            lottieTrash = itemView.findViewById(R.id.lottieTrash);
        }
    }

    public interface PlannerListener {
        void onDeleteMeal(PlannedMeal meal);
    }
    public interface MealClickListener {
        void onMealClicked(Meal meal);
    }
}

