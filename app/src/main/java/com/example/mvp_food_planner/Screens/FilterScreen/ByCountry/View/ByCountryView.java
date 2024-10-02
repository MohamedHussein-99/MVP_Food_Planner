package com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;

import java.util.List;

public interface ByCountryView {
    void showCountries(List<CountryFilter> countries);
    void showMealList(List<Meal> meals);
    void showError(String message);
}
