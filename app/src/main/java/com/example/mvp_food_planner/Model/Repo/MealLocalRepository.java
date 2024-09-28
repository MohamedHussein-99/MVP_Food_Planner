package com.example.mvp_food_planner.Model.Repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvp_food_planner.DataBase.MealDao;
import com.example.mvp_food_planner.DataBase.MealsDataBase;
import com.example.mvp_food_planner.Model.Entity.Meal;

import java.util.List;

public class MealLocalRepository {
    private MealDao mealDao;
    private LiveData<List<Meal>> allMeals;

    public MealLocalRepository(Context context) {
        MealsDataBase db = MealsDataBase.getInstance(context);
        mealDao = db.getMealDao();
        allMeals = mealDao.getAllMeals();
    }

    public LiveData<List<Meal>> getAllMeals() {
        return allMeals;
    }

    public void insert(Meal meal) {
        MealsDataBase.databaseWriteExecutor.execute(() -> mealDao.insert(meal));
    }

    public void delete(Meal meal) {
        MealsDataBase.databaseWriteExecutor.execute(() -> mealDao.deleteMeal(meal));
    }
}
