package com.example.mvp_food_planner.Model.Repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mvp_food_planner.DataBase.MealSavedDao;
import com.example.mvp_food_planner.DataBase.MealsDataBase;
import com.example.mvp_food_planner.Model.Entity.Meal;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MealLocalRepository {
    private MealSavedDao mealSaveDAO;
    private final ExecutorService executorService;

    public MealLocalRepository(Context context) {
        MealsDataBase db = MealsDataBase.getInstance(context);
        mealSaveDAO = db.getMealDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Meal>> getSavedMeals() {
        return mealSaveDAO.getMeals();
    }

    public void insertSavedMeal(Meal meal) {
        executorService.execute(() ->{
            Log.d("MealLocalRepository", "Inserting meal: " + meal.strMeal);
            mealSaveDAO.insert(meal);
        });
    }

    public void deleteSavedMeal(Meal meal) {
        executorService.execute(() -> mealSaveDAO.deleteMeal(meal));
    }

    // Check if the meal exists and trigger a callback
    public void isMealExists(String mealId, MealExistsCallback callback) {
        executorService.execute(() -> {
            boolean exists = mealSaveDAO.isMealExists(mealId);
            new Handler(Looper.getMainLooper()).post(() -> callback.onResult(exists));
        });
    }

    public interface MealExistsCallback {
        void onResult(boolean isExists);
    }
}
