package com.example.mvp_food_planner.Screens.FilterScreen.ByIngredients.View;

import com.example.mvp_food_planner.Model.POJO.IngredientFilter;

import java.util.List;

public interface IngredientView {
    void showIngredients(List<IngredientFilter> ingredientList);
    void showError(String errorMessage);
}
