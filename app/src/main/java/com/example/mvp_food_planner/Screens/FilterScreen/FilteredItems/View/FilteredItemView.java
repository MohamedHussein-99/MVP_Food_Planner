package com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View;

import com.example.mvp_food_planner.Model.Entity.Meal;

import java.util.List;

public interface FilteredItemView {
    void showMealList(List<Meal> randomList);
    void showError(String error);
}
