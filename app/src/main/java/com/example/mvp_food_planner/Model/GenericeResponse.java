package com.example.mvp_food_planner.Model;

import java.util.List;

public class GenericeResponse<T> {
    public List<T> meals;
    public List<T> categories; // Used for categories response

    // to debug Category
    public List<T> getMeals() {
        return meals;
    }
    public List<T> getCategories() {
        return categories;
    }

}
