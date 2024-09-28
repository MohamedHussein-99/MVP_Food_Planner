package com.example.mvp_food_planner.Screens.MealDetailsScreen.Presenter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Repo.MealLocalRepository;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.Network.NetworkCallback;
import com.example.mvp_food_planner.Screens.MealDetailsScreen.View.DetailsView;

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
                view.showMealDetails(items.get(0));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.showError(errorMsg);
            }
        });
    }
    // detach the view to prevent memory leaks in case of activity or fragment destruction
    public void detachView() {
        this.view = null;
    } // will be used at the end

    public void saveMeal(Meal meal) {
        repository.insert(meal);
    }

    public void removeMeal(Meal meal) {
        repository.delete(meal);
    }

}
