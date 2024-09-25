package com.example.mvp_food_planner.Model.Repo;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.Network.NetworkCallback;

public class Repo {
    private final Client client;

    public Repo() {
        client = Client.getInstance();
    }

    public void fetchRandomMeal(NetworkCallback<Meal> callback) {
        client.getRandomMeal(callback);
    }

    public void fetchCategories(NetworkCallback<CategoryFilter> callback) {
        client.getCategoriesList(callback);
    }

    public void fetchCountries(NetworkCallback<CountryFilter> callback) {
        client.getCountriesList(callback);
    }
}
