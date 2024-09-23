package com.example.mvp_food_planner.Network;

import com.example.mvp_food_planner.Model.CategoryResponse;
import com.example.mvp_food_planner.Model.CountryResponse;
import com.example.mvp_food_planner.Model.IngredientFilter;
import com.example.mvp_food_planner.Model.IngredientResponse;
import com.example.mvp_food_planner.Model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    // Meal API
    @GET("random.php")
    Call<MealResponse> getMeal();

    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("list.php?a=list")
    Call<CountryResponse> getCountries();

    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredients();


}
