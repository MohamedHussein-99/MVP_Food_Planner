package com.example.mvp_food_planner.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvp_food_planner.Model.Entity.PlannedMeal;

import java.util.Date;
import java.util.List;

@Dao
public interface MealPlannedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPLannedMeal(PlannedMeal meal);

    @Delete
    void deletePlannedMeal(PlannedMeal meal);

    @Query("SELECT * FROM planMeal_table WHERE date BETWEEN :startOfDay AND :endOfDay")
    LiveData<List<PlannedMeal>> getMealForDay(Date startOfDay, Date endOfDay);


    @Query("SELECT * FROM planMeal_table")
    LiveData<List<PlannedMeal>> getAllPlannedMeals();
}
