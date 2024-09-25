package com.example.mvp_food_planner.Model.POJO;

import androidx.room.TypeConverter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

// Converts Meal objects to JSON and vice versa //
public class MealConverter {
    @TypeConverter
    public static String fromMealList(List<Meal> meals) {
        if (meals == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(meals);
    }

    @TypeConverter
    public static List<Meal> toMealList(String mealJson) {
        if (mealJson == null) {
            return null;
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Meal>>() {}.getType();
        return gson.fromJson(mealJson, listType);
    }

}
