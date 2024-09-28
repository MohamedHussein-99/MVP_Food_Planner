package com.example.mvp_food_planner.Screens.FavScreen.Presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Repo.MealLocalRepository;
import com.example.mvp_food_planner.Screens.FavScreen.View.FavoritesView;

import java.util.List;

public class FavPresenter {
    private FavoritesView view;
    private MealLocalRepository repository;
    private LiveData<List<Meal>> favoriteMeals;

    public FavPresenter(FavoritesView view, MealLocalRepository repository) {
        this.view = view;
        this.repository = repository;
        observeFavoriteMeals();
    }

    // This method observes the favorite meals and updates the view when data changes
    private void observeFavoriteMeals() {
        favoriteMeals = repository.getSavedMeals(); // We will observe the LiveData here

        // Observe LiveData for any changes and update the view accordingly
        favoriteMeals.observe((LifecycleOwner) view, meals -> {
            if (meals != null && !meals.isEmpty()) {
                view.showFavoriteMeals(meals);
            } else {
                view.showError("No favorite meals found");
            }
        });
    }
}
