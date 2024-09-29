package com.example.mvp_food_planner.Screens.PlannerScreen.View;

import com.example.mvp_food_planner.Model.Entity.PlannedMeal;

import java.util.List;

public interface PlannerView {
    void showPlannedMeals(List<PlannedMeal> meals);
    void showError(String error);
}
