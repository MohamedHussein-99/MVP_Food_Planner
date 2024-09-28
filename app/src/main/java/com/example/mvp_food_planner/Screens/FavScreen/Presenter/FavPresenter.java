package com.example.mvp_food_planner.Screens.FavScreen.Presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Repo.MealLocalRepository;
import com.example.mvp_food_planner.Screens.FavScreen.View.FavoritesView;

import java.util.List;

public class FavPresenter {
    private FavoritesView view;
    private MealLocalRepository repository;


    public FavPresenter(FavoritesView view, MealLocalRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void loadSavedMeals() {
        LiveData<List<Meal>> savedList = repository.getSavedMeals();
        savedList.observeForever(new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                view.showFavoriteMeals(meals);
            }
        });
    }

    public void removeFromSaved(Meal meal) {
        repository.deleteSavedMeal(meal);
    }

}
