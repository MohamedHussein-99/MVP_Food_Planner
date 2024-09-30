package com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View;

import com.example.mvp_food_planner.Model.POJO.CategoryFilter;

import java.util.List;

public interface CategoryView {
    void displayCategories(List<CategoryFilter> categories);
    void showError(String message);
}
