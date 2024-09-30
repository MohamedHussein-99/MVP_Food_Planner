package com.example.mvp_food_planner.Screens.FilterScreen.ByIngredients.Presenter;

import com.example.mvp_food_planner.Model.POJO.IngredientFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.NetworkCallback;
import com.example.mvp_food_planner.Screens.FilterScreen.ByIngredients.View.IngredientView;

import java.util.List;

public class ByIngredientPresenter {
    private final IngredientView view;
    private final Repo repository;

    public ByIngredientPresenter(IngredientView view, Repo repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getIngredients() {
        repository.fetchIngredients(new NetworkCallback<IngredientFilter>() {
            @Override
            public void onSuccess(List<IngredientFilter> ingredients) {
                view.showIngredients(ingredients);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}

