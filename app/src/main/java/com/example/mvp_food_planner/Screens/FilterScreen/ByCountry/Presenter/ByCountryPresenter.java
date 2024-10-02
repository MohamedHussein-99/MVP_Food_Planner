package com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.Presenter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View.ByCountryView;
import com.example.mvp_food_planner.Network.NetworkCallback;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View.FilteredItemView;

import java.util.List;
import java.util.stream.Collectors;

public class ByCountryPresenter {
    private final ByCountryView view;
    private final Repo repo;

    public ByCountryPresenter(ByCountryView view, Repo repo) {
        this.view = view;
        this.repo = repo;
    }

    public void fetchAreas() {
        repo.fetchCountries(new NetworkCallback<CountryFilter>() {
            @Override
            public void onSuccess(List<CountryFilter> areas) {
                view.showCountries(areas);
            }

            @Override
            public void onFailure(String errMsg) {
                view.showError(errMsg);
            }
        });
    }

    public void fetchMealsByArea(String area) {
        repo.fetchMealsByArea(area, new NetworkCallback<Meal>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                view.showMealList(meals);
            }

            @Override
            public void onFailure(String errMsg) {
                view.showError(errMsg);
            }
        });
    }
}




