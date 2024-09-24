// app/src/main/java/com/example/mvp_food_planner/Network/Client.java
package com.example.mvp_food_planner.Network;

import androidx.annotation.NonNull;

import com.example.mvp_food_planner.Model.CategoryFilter;
import com.example.mvp_food_planner.Model.CountryFilter;
import com.example.mvp_food_planner.Model.GenericeResponse;
import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.IngredientFilter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final ApiServices service;
    private static Client client;

    private Client() {
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

    // Generic method to handle different requests
    private <T> void fetchData(Call<GenericeResponse<T>> call, NetworkCallback<T> callback) {
        call.enqueue(new Callback<GenericeResponse<T>>() {
            @Override
            public void onResponse(@NonNull Call<GenericeResponse<T>> call, @NonNull retrofit2.Response<GenericeResponse<T>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().meals);
                } else {
                    callback.onFailure("Failed to get data");
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericeResponse<T>> call, @NonNull Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getCategoriesList(NetworkCallback<CategoryFilter> callback) {
        fetchData(service.getCategories(), callback);
    }

    public void getIngredientsList(NetworkCallback<IngredientFilter> callback) {
        fetchData(service.getIngredients(), callback);
    }

    public void getCountriesList(NetworkCallback<CountryFilter> callback) {
        fetchData(service.getCountries(), callback);
    }

    public void getRandomMeal(NetworkCallback<Meal> callback) {
        fetchData(service.getMeal(), callback);
    }
}