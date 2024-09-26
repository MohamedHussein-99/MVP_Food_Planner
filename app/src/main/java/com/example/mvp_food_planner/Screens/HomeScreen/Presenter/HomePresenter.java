package com.example.mvp_food_planner.Screens.HomeScreen.Presenter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.NetworkCallback;
import com.example.mvp_food_planner.Screens.HomeScreen.View.HomeView;

import java.util.List;

public class HomePresenter {
    private final HomeView view;
    private final Repo repository;

    //  constructor to take Repo directly
    public HomePresenter(HomeView view, Repo repository) {
        this.view = view;
        this.repository = repository;
    }

    // Count parameter to fetch multiple meals instead of one
    public void getRandomMeals(int count) {
        repository.fetchRandomMeals(count, new NetworkCallback<Meal>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                view.getRandMeal(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.getError(errorMessage);
            }
        });
    }

    public void getCategories() {
        repository.fetchCategories(new NetworkCallback<CategoryFilter>() {
            @Override
            public void onSuccess(List<CategoryFilter> categories) {
                view.getCategories(categories);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.getError(errorMessage);
            }
        });
    }

    public void getCountries() {
        repository.fetchCountries(new NetworkCallback<CountryFilter>() {
            @Override
            public void onSuccess(List<CountryFilter> countries) {
                view.getCountry(countries);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.getError(errorMessage);
            }
        });
    }
}
