package com.example.mvp_food_planner.Screens.MealDetailsScreen.Presenter;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.Network.NetworkCallback;
import com.example.mvp_food_planner.Screens.MealDetailsScreen.View.DetailsView;

import java.util.List;

public class MealDetailsPresenter {
    private DetailsView view;
    private Client client;

    public MealDetailsPresenter(DetailsView view, Client client) {
        this.view = view;
        this.client = client;
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
    }

}
