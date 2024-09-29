package com.example.mvp_food_planner.Screens.MealDetailsScreen.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Repo.MealLocalRepository;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.MealDetailsScreen.Presenter.MealDetailsPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MealDetailsFragment extends Fragment implements DetailsView {

    private MealDetailsPresenter presenter;
    private TextView title, categoryArea, instructions;
    private WebView videoView;
    private ImageView thumbnail;
    private RecyclerView recyclerView;
    private MealDetailsAdapter adapter;
    private CheckBox cbHeart;
    private Meal meal;

    //@SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_click_meal, container, false);

        title = view.findViewById(R.id.txtRecipeTitle);
        categoryArea = view.findViewById(R.id.txtCat);
        instructions = view.findViewById(R.id.txtInstructions);
        videoView = view.findViewById(R.id.videoView);
        thumbnail = view.findViewById(R.id.imgMealDetails);
        recyclerView = view.findViewById(R.id.recyclerIngrediant);
        cbHeart = view.findViewById(R.id.cbHeart);

        presenter = new MealDetailsPresenter(this, new Client() , new MealLocalRepository(getContext()));

        // setup recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MealDetailsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String mealId = bundle.getString("mealId");
            if (mealId != null) {
                presenter.getMealDetails(mealId);
            }
        }
        cbHeart.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (meal != null) {
                if (isChecked) {
                    presenter.saveMeal(meal);
                    showUndoSnackbar(view, "Meal added to favorites", () -> {
                        presenter.deleteMeal(meal);
                        cbHeart.setChecked(false);  // Uncheck the box when undoing
                    });
                } else {
                    presenter.deleteMeal(meal);
                    showUndoSnackbar(view, "Meal removed from favorites", () -> {
                        presenter.saveMeal(meal);
                        cbHeart.setChecked(true);  // Check the box when undoing
                    });
                }
            } else {
                Toast.makeText(getContext(), "Meal is null", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void showMealDetails(Meal meal) {
        this.meal = meal;
        Glide.with(getContext()).load(meal.strMealThumb)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(thumbnail);

        title.setText(meal.strMeal);
        categoryArea.setText(meal.strCategory + " | " + meal.strArea);

        // Use HashMap to store ingredient and measure pairs
        Map<String, String> ingredientsMap = new HashMap<>();
        addIngredient(ingredientsMap, meal.strIngredient1, meal.strMeasure1);
        addIngredient(ingredientsMap, meal.strIngredient2, meal.strMeasure2);
        addIngredient(ingredientsMap, meal.strIngredient3, meal.strMeasure3);
        addIngredient(ingredientsMap, meal.strIngredient4, meal.strMeasure4);
        addIngredient(ingredientsMap, meal.strIngredient5, meal.strMeasure5);
        addIngredient(ingredientsMap, meal.strIngredient6, meal.strMeasure6);
        addIngredient(ingredientsMap, meal.strIngredient7, meal.strMeasure7);
        addIngredient(ingredientsMap, meal.strIngredient8, meal.strMeasure8);
        addIngredient(ingredientsMap, meal.strIngredient9, meal.strMeasure9);
        addIngredient(ingredientsMap, meal.strIngredient10, meal.strMeasure10);
        addIngredient(ingredientsMap, meal.strIngredient11, meal.strMeasure11);
        addIngredient(ingredientsMap, meal.strIngredient12, meal.strMeasure12);
        addIngredient(ingredientsMap, meal.strIngredient13, meal.strMeasure13);
        addIngredient(ingredientsMap, meal.strIngredient14, meal.strMeasure14);
        addIngredient(ingredientsMap, meal.strIngredient15, meal.strMeasure15);

        // Pass the ingredients to the adapter using the instance (non-static)
        adapter.setIngredients(new ArrayList<>(ingredientsMap.entrySet()));

        instructions.setText(meal.strInstructions);

        videoView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript for the WebView to play the video
        String videoUrl = meal.strYoutube.replace("watch?v=", "embed/");
        videoView.loadUrl(videoUrl);

    }

    private void addIngredient(Map<String, String> ingredientsMap, String ingredient, String measure) {
        if (ingredient != null && !ingredient.isEmpty()) {
            ingredientsMap.put(ingredient, measure);
        }
    }

    @Override
    public void setFavoriteState(boolean isFavorite) {
        cbHeart.setChecked(isFavorite);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    private void showUndoSnackbar(View view, String message, Runnable undoAction) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", v -> undoAction.run());
        snackbar.show();
    }
}
