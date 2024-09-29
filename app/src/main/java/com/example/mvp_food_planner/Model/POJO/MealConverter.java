package com.example.mvp_food_planner.Model.POJO;

import androidx.room.TypeConverter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

// Converts Date objects to JSON and vice versa //
public class MealConverter {

    @TypeConverter
    public static Long fromDate(Date date) {
        return date != null ? date.getTime() : null;
    }

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp != null ? new Date(timestamp) : null;
    }

}
