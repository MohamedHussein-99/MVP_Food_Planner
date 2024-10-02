package com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.Presenter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.NetworkCallback;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View.FilteredItemView;

import java.util.List;

public class FilteredItemPresenter {
    private final FilteredItemView view;
    private final Repo repository;

    public FilteredItemPresenter(FilteredItemView view, Repo repository) {
        this.view = view;
        this.repository = repository;
    }

    public void fetchMealsByCategory(String category) {
        repository.fetchMealsByCategory(category, new NetworkCallback<Meal>() {
            @Override
            public void onSuccess(List<Meal> response) {
                view.showMealList(response);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    public void fetchMealsByArea(String area) {
        repository.fetchMealsByArea(area, new NetworkCallback<Meal>() {
            @Override
            public void onSuccess(List<Meal> response) {
                view.showMealList(response);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    public void fetchMealsByIngredient(String ingredient) {
        repository.fetchMealsByIngredient(ingredient, new NetworkCallback<Meal>() {
            @Override
            public void onSuccess(List<Meal> response) {
                view.showMealList(response);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}
