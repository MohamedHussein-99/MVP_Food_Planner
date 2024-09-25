package com.example.mvp_food_planner.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.MealConverter;

@Database(entities = {Meal.class}, version = 1 , exportSchema = false)
@TypeConverters({MealConverter.class}) // to convert the list of ingredients to string
public abstract class MealsDataBase extends RoomDatabase {

    public abstract MealDao getMealDao();
    private static MealsDataBase instance = null;

    public static synchronized MealsDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MealsDataBase.class, "mealsdb")
                    .build();
        }
        return instance;
    }
}
