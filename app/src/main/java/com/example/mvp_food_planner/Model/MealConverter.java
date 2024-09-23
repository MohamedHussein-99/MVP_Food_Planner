package com.example.mvp_food_planner.Model;

import androidx.room.TypeConverter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.google.gson.Gson;

public class MealConverter {
    @TypeConverter
    public String fromMeal(Meal meal) {
        Gson gson = new Gson();
        return gson.toJson(meal);
    }

    @TypeConverter
    public Meal toMeal(String mealJson) {
        Gson gson = new Gson();
        return gson.fromJson(mealJson, Meal.class);
    }
}
