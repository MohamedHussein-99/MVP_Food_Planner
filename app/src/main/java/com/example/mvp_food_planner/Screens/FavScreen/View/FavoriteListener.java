package com.example.mvp_food_planner.Screens.FavScreen.View;

import com.example.mvp_food_planner.Model.Entity.Meal;

public interface FavoriteListener {
    void onRemoveFromSaved(Meal meal);
    void onMealClicked(Meal meal);
}
