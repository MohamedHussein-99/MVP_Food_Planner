package com.example.mvp_food_planner.Screens.MealDetailsScreen.View;

import com.example.mvp_food_planner.Model.Entity.Meal;

public interface checkboxListener {
    void onCheckboxClicked(Meal meal);
    void onCheckboxUnClicked(Meal meal);
}
