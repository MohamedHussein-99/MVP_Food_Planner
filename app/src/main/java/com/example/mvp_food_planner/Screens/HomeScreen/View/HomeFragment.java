package com.example.mvp_food_planner.Screens.HomeScreen.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View.ByCategoryAdapter;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View.ByCountryAdapter;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View.FilteredItemFragment;
import com.example.mvp_food_planner.Screens.HomeScreen.Presenter.HomePresenter;
import com.example.mvp_food_planner.Screens.MealDetailsScreen.View.MealDetailsFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeView, RandomMealAdapter.MealClickListener, ByCountryAdapter.AreaClickListener, ByCategoryAdapter.CategoryClickListener {

    private HomePresenter presenter;
    private RandomMealAdapter mealAdapter;
    private List<Meal> randomMeals = new ArrayList<>();
    private List<CategoryFilter> categories = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private ByCountryAdapter countryAdapter;
    private List<CountryFilter> countries = new ArrayList<>();

    // Progress Bar Variables
    private ProgressBar progressMeal;
    private ProgressBar progressCategory;
    private ProgressBar progressCountry;

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Repo repo = new Repo(Client.getInstance());
        presenter = new HomePresenter(this, repo);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        // Initialize Progress Bars
        progressMeal = view.findViewById(R.id.progressMeal);
        progressCategory = view.findViewById(R.id.progressCategory);
        progressCountry = view.findViewById(R.id.progressCountry);

        // Setup random meals RecyclerView
        RecyclerView mealRecyclerView = view.findViewById(R.id.recyclerRandomMeal);
        mealAdapter = new RandomMealAdapter(getContext(), randomMeals, this);
        mealRecyclerView.setNestedScrollingEnabled(false);
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mealRecyclerView.setAdapter(mealAdapter);

        // Setup categories RecyclerView
        RecyclerView categoryRecyclerView = view.findViewById(R.id.recyclerCategory);
        categoryAdapter = new CategoryAdapter(getContext(), categories, this);
        categoryRecyclerView.setNestedScrollingEnabled(false);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoryAdapter);

        // Setup countries RecyclerView
        RecyclerView countryRecyclerView = view.findViewById(R.id.recyclerCountry);
        countryAdapter = new ByCountryAdapter(getContext(), countries, this);
        countryRecyclerView.setNestedScrollingEnabled(false);
        countryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
        countryRecyclerView.setAdapter(countryAdapter);

        // Fetch initial data
        fetchInitialData();

        return view;
    }

    private void fetchInitialData() {
        showLoading(true); // Show loading progress
        presenter.getCategories();
        presenter.getRandomMeals(5);
        presenter.getCountries();
    }

    // Show or hide progress bars
    private void showLoading(boolean isLoading) {
        if (progressMeal != null) {
            progressMeal.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }
        if (progressCategory != null) {
            progressCategory.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }
        if (progressCountry != null) {
            progressCountry.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }
    }

    // Handle random meals updates
    @Override
    public void getRandMeal(List<Meal> meals) {
        this.randomMeals.clear();
        this.randomMeals.addAll(meals);
        mealAdapter.notifyDataSetChanged();
        showLoading(false); // Hide progress bar when data is loaded
    }

    // Handle categories updates
    @Override
    public void getCategories(List<CategoryFilter> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        categoryAdapter.notifyDataSetChanged();
        showLoading(false); // Hide progress bar when data is loaded
    }

    // Handle country updates
    @Override
    public void getCountry(List<CountryFilter> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        countryAdapter.notifyDataSetChanged();
        showLoading(false); // Hide progress bar when data is loaded
    }

    @Override
    public void getError(String message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), "No Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        showLoading(false); // Hide progress bar on error
    }

    // Handle meal click events
    @Override
    public void onMealClicked(Meal meal) {
        if (meal != null && meal.idMeal != null) {
            Bundle bundle = new Bundle();
            bundle.putString("mealId", meal.idMeal);
            MealDetailsFragment detailsFragment = new MealDetailsFragment();
            detailsFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentNav, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    // Handle country click events
    @Override
    public void onAreaClicked(CountryFilter country) {
        Bundle bundle = new Bundle();
        bundle.putString("selectedArea", country.getStrArea());
        FilteredItemFragment filteredItemFragment = new FilteredItemFragment();
        filteredItemFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentNav, filteredItemFragment)
                .addToBackStack(null)
                .commit();
    }

    // Handle category click events
    @Override
    public void onCategoryClick(String category) {
        Bundle bundle = new Bundle();
        bundle.putString("selectedCategory", category);
        FilteredItemFragment filteredItemFragment = new FilteredItemFragment();
        filteredItemFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentNav, filteredItemFragment)
                .addToBackStack(null)
                .commit();
    }
}
