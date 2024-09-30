// app/src/main/java/com/example/mvp_food_planner/Network/Client.java
package com.example.mvp_food_planner.Network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Model.GenericeResponse;
import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.IngredientFilter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final ApiServices service;
    private static Client client;
    private static final String TAG = "Client";

    public Client() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiServices.class);
    }

    public static Client getInstance() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    // Generic method to fetch data from the API
    // Boolean to avoid fetching category error
    private <T> void fetchData(Call<GenericeResponse<T>> call, NetworkCallback<T> callback, boolean isCategory) {
        call.enqueue(new Callback<GenericeResponse<T>>() {
            @Override
            public void onResponse(@NonNull Call<GenericeResponse<T>> call, @NonNull retrofit2.Response<GenericeResponse<T>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Raw response: " + response.body().toString());
                    if (isCategory && response.body().getCategories() != null) {
                        callback.onSuccess(response.body().getCategories());
                    } else if (!isCategory && response.body().getMeals() != null) {
                        callback.onSuccess(response.body().getMeals());
                    } else {
                        callback.onFailure("Failed to get data");
                    }
                } else {
                    callback.onFailure("Failed to get data: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericeResponse<T>> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    // Fetch meals by category
    public void getMealsByCategory(String category, NetworkCallback<Meal> callback) {
        fetchData(service.getMealsByCategory(category), callback, false);
    }

    // Fetch meals by area (country)
    public void getMealsByArea(String area, NetworkCallback<Meal> callback) {
        fetchData(service.getMealsByArea(area), callback, false);
    }

    // Fetch meals by ingredient
    public void getMealsByIngredient(String ingredient, NetworkCallback<Meal> callback) {
        fetchData(service.getMealsByIngredient(ingredient), callback, false);
    }

    public void getCategoriesList(NetworkCallback<CategoryFilter> callback) {
        fetchData(service.getCategories(), callback, true);
    }

    public void getIngredientsList(NetworkCallback<IngredientFilter> callback) {
        fetchData(service.getIngredients(), callback, false);
    }

    public void getCountriesList(NetworkCallback<CountryFilter> callback) {
        fetchData(service.getCountries(), callback, false);
    }

    public void getRandomMeal(NetworkCallback<Meal> callback) {
        fetchData(service.getMeal(), callback, false);
    }

    // Generic method for fetching a single item by ID
    private <T> void fetchById(Call<GenericeResponse<T>> call, NetworkCallback<T> callback) {
        call.enqueue(new Callback<GenericeResponse<T>>() {
            @Override
            public void onResponse(@NonNull Call<GenericeResponse<T>> call, @NonNull retrofit2.Response<GenericeResponse<T>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getMeals() != null) {
                    callback.onSuccess(response.body().getMeals());
                } else {
                    callback.onFailure("Failed to get item by ID");
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericeResponse<T>> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getMealById(String mealId, NetworkCallback<Meal> callback) {
        Call<GenericeResponse<Meal>> call = service.getMealById(mealId);
        fetchById(call, callback); // Use the generic fetchById method
    }
}
