package com.example.mvp_food_planner.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.MealConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Meal.class}, version = 2 , exportSchema = false)
@TypeConverters({MealConverter.class}) // to convert the list of ingredients to string
public abstract class MealsDataBase extends RoomDatabase {

    public abstract MealDao getMealDao();
    private static MealsDataBase instance = null;

    // Executor for background database operations
    private static final int NUMBER_OF_THREADS = 4; // handle database operations on background threads.
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized MealsDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MealsDataBase.class, "mealsdb")
                    .fallbackToDestructiveMigration()  // Allow destructive migration
                     .build();
        }
        return instance;
    }
}
