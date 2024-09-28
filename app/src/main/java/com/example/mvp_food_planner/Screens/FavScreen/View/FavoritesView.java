package com.example.mvp_food_planner.Screens.FavScreen.View;

import com.example.mvp_food_planner.Model.Entity.Meal;

import java.util.List;

public interface FavoritesView {
    void showFavoriteMeals(List<Meal> meals);
    void showError(String error);
}
