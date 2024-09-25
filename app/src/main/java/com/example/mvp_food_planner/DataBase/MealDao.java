package com.example.mvp_food_planner.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvp_food_planner.Model.Entity.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Query("SELECT * FROM recipe_table")
    LiveData<List<Meal>> getAllMeals();

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Meal Meal);

    @Delete
    void deleteMeal(Meal Meal);

}
