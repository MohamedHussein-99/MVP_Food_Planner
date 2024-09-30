package com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.Presenter;

import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.NetworkCallback;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View.CategoryView;

import java.util.List;

public class ByCategoryPresenter {
    private final CategoryView view;
    private final Repo repository;

    public ByCategoryPresenter(CategoryView view, Repo repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getCategories() {
        repository.fetchCategories(new NetworkCallback<CategoryFilter>() {
            @Override
            public void onSuccess(List<CategoryFilter> categories) {
                view.displayCategories(categories);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}



