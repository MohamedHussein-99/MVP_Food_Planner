package com.example.mvp_food_planner.Screens.MealDetailsScreen.View;

import com.example.mvp_food_planner.Model.Entity.Meal;

public interface DetailsView {
    void showMealDetails(Meal meal);
    void showError(String error);
}
