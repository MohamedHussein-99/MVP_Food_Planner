package com.example.mvp_food_planner.Screens.PlannerScreen.Presenter;

import androidx.lifecycle.LiveData;

import androidx.lifecycle.Observer;

import com.example.mvp_food_planner.Model.Entity.PlannedMeal;
import com.example.mvp_food_planner.Model.Repo.MealLocalRepository;
import com.example.mvp_food_planner.Screens.PlannerScreen.View.PlannerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlannerPresenter {
    private PlannerView view;
    private MealLocalRepository repository;

    public PlannerPresenter(PlannerView view, MealLocalRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void loadMealsForDay(Date day) {
        // Create Calendar instance for the selected day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);

        // Set the time to the beginning and end of the day
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfDay = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endOfDay = calendar.getTime();

        // Fetch meals planned for the entire day range
        LiveData<List<PlannedMeal>> plannedMeals = repository.getPlannedMealsForDay(startOfDay, endOfDay);
        plannedMeals.observeForever(new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                if (meals != null && !meals.isEmpty()) {
                    view.showPlannedMeals(meals);
                } else {
                    // Pass an empty list to the view
                    view.showPlannedMeals(new ArrayList<>());
                }
            }
        });
    }


    public void deletePlannedMeal(PlannedMeal meal) {
        repository.deletePlannedMeal(meal);
    }
}

