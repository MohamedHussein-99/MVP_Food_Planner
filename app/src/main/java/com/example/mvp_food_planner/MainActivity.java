// app/src/main/java/com/example/mvp_food_planner/MainActivity.java
package com.example.mvp_food_planner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mvp_food_planner.Model.CategoryFilter;
import com.example.mvp_food_planner.Model.CountryFilter;
import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.IngredientFilter;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.Network.NetworkCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /* testing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Client client = Client.getInstance();

        // Test getCategoriesList API
        client.getCategoriesList(new NetworkCallback<CategoryFilter>() {
            @Override
            public void onSuccess(List<CategoryFilter> response) {
                if (response != null) {
                    Log.d(TAG, "Categories: " + response.toString());
                } else {
                    Log.e(TAG, "Categories response is null");
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, "Failed to get categories: " + errorMessage);
            }
        });

        // Test getIngredientsList API
        client.getIngredientsList(new NetworkCallback<IngredientFilter>() {
            @Override
            public void onSuccess(List<IngredientFilter> response) {
                if (response != null) {
                    Log.d(TAG, "Ingredients: " + response.toString());
                } else {
                    Log.e(TAG, "Ingredients response is null");
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, "Failed to get ingredients: " + errorMessage);
            }
        });

        // Test getCountriesList API
        client.getCountriesList(new NetworkCallback<CountryFilter>() {
            @Override
            public void onSuccess(List<CountryFilter> response) {
                if (response != null) {
                    Log.d(TAG, "Countries: " + response.toString());
                } else {
                    Log.e(TAG, "Countries response is null");
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, "Failed to get countries: " + errorMessage);
            }
        });

        // Test getRandomMeal API
        client.getRandomMeal(new NetworkCallback<Meal>() {
            @Override
            public void onSuccess(List<Meal> response) {
                if (response != null) {
                    Log.d(TAG, "Random Meal: " + response.toString());
                } else {
                    Log.e(TAG, "Random Meal response is null");
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, "Failed to get random meal: " + errorMessage);
            }
        });
    }
    */

    LottieAnimationView lottieStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lottieStart = findViewById(R.id.lottieStart);
        lottieStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, homeActivity.class);
                startActivity(intent);
            }
        });

    }

}