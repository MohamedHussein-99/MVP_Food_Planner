package com.example.mvp_food_planner.Screens.HomeScreen.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.HomeScreen.Presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeView, RandomMealAdapter.MealClickListener {

    private HomePresenter presenter;
    private RandomMealAdapter mealAdapter;
    private List<Meal> randomMeals = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this, new Client());
        presenter.getCategories();
        presenter.getRandomMeals(5); // Fetch 5 random meals
        presenter.getCountries();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView mealRecyclerView = view.findViewById(R.id.recyclerRandomMeal);
        mealAdapter = new RandomMealAdapter(getContext(), randomMeals, this);
        mealRecyclerView.setNestedScrollingEnabled(false); // Disable nested scrolling

        mealRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mealRecyclerView.setAdapter(mealAdapter);

        return view;
    }

    @Override
    public void getRandMeal(List<Meal> meals) {
        this.randomMeals.clear();
        this.randomMeals.addAll(meals);
        mealAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategories(List<CategoryFilter> categories) {
        // Handle updating the categories RecyclerView
    }

    @Override
    public void getCountry(List<CountryFilter> countries) {
        // Handle updating the countries RecyclerView
    }

    @Override
    public void getError(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClicked(Meal meal) {
        Toast.makeText(getContext(), meal.getStrMeal(), Toast.LENGTH_SHORT).show();
    }
}

