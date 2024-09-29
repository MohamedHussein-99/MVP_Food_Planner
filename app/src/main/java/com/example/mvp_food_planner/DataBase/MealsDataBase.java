package com.example.mvp_food_planner.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Entity.PlannedMeal;
import com.example.mvp_food_planner.Model.POJO.MealConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Meal.class , PlannedMeal.class}, version = 2 , exportSchema = false)
@TypeConverters({MealConverter.class}) // to convert the Date
public abstract class MealsDataBase extends RoomDatabase {

    public abstract MealSavedDao getMealDao();
    public abstract MealPlannedDao getMealPlannedDao();
    private static MealsDataBase instance = null;


    public static synchronized MealsDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MealsDataBase.class, "mealsdb")
                    .fallbackToDestructiveMigration()  // Allow destructive migration
                     .build();
        }
        return instance;
    }
}
