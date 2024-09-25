package com.example.mvp_food_planner.Screens.HomeScreen.View;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;

import java.util.List;

public interface HomeView {
    void getRandMeal(List<Meal> meals);
    void getCategories(List<CategoryFilter> categories);
    void getCountry(List<CountryFilter> country);
    void getError(String errMsg);

}
