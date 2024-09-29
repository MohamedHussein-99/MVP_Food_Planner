package com.example.mvp_food_planner.Screens.MealDetailsScreen.Presenter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Entity.PlannedMeal;
import com.example.mvp_food_planner.Model.Repo.MealLocalRepository;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.Network.NetworkCallback;
import com.example.mvp_food_planner.Screens.MealDetailsScreen.View.DetailsView;

import java.util.Date;
import java.util.List;

public class MealDetailsPresenter {
    private DetailsView view;
    private Client client;
    private MealLocalRepository repository;

    public MealDetailsPresenter(DetailsView view, Client client , MealLocalRepository repository) {
        this.view = view;
        this.client = client;
        this.repository = repository;
    }

    public void getMealDetails(String mealId) {
        client.getMealById(mealId, new NetworkCallback<Meal>() {
            @Override
            public void onSuccess(List<Meal> items) {
                Meal meal = items.get(0);
                view.showMealDetails(items.get(0));
                // Check if the meal is already in the favorites and update the checkbox state
                repository.isMealExists(meal.idMeal, isExists -> {
                    view.setFavoriteState(isExists);
                });
            }

            @Override
            public void onFailure(String errorMsg) {
                view.showError(errorMsg);
            }
        });
    }

    public void saveMeal(Meal meal) {
        repository.insertSavedMeal(meal);
    }

    public void deleteMeal(Meal meal) {
        repository.deleteSavedMeal(meal);
    }

    // Saving a meal to planned meals
    public void savePlannedMeal(Meal meal, Date date) {
        PlannedMeal plannedMeal = convertToPlannedMeal(meal, date);
        repository.insertPlannedMeal(plannedMeal);
    }

    private PlannedMeal convertToPlannedMeal(Meal meal, Date date) {
        PlannedMeal plannedMeal = new PlannedMeal();
        plannedMeal.idMeal = meal.idMeal;
        plannedMeal.strMeal = meal.strMeal;
        plannedMeal.strMealThumb = meal.strMealThumb;
        plannedMeal.strCategory = meal.strCategory;
        plannedMeal.strArea = meal.strArea;
        plannedMeal.strInstructions = meal.strInstructions;
        plannedMeal.date = date; // Assign the date here
        return plannedMeal;
    }

}
