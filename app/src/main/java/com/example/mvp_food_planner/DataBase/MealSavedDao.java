package com.example.mvp_food_planner.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvp_food_planner.Model.Entity.Meal;

import java.util.List;

@Dao
public interface MealSavedDao {
    @Query("SELECT * FROM fav_meals_table")
    LiveData<List<Meal>> getMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Meal Meal);

    @Delete
    void deleteMeal(Meal Meal);

    @Query("SELECT COUNT(*) > 0 FROM fav_meals_table WHERE idMeal = :mealId")
    boolean isMealExists(String mealId);

    @Query("SELECT * FROM fav_meals_table WHERE idMeal = :mealId LIMIT 1")
    LiveData<Meal> getMealById(String mealId);
}
