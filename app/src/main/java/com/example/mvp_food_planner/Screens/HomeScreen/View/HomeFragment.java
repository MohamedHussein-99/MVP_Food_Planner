package com.example.mvp_food_planner.Screens.HomeScreen.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View.ByCountryAdapter;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View.FilteredItemFragment;
import com.example.mvp_food_planner.Screens.HomeScreen.Presenter.HomePresenter;
import com.example.mvp_food_planner.Screens.MealDetailsScreen.View.MealDetailsFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeView, RandomMealAdapter.MealClickListener, ByCountryAdapter.AreaClickListener {

    private HomePresenter presenter;
    private RandomMealAdapter mealAdapter;
    private List<Meal> randomMeals = new ArrayList<>();
    private List<CategoryFilter> categories = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private ByCountryAdapter countryAdapter;
    private List<CountryFilter> countries = new ArrayList<>(); // Country list

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create Repo instance and pass it to the presenter
        Repo repo = new Repo(Client.getInstance());
        presenter = new HomePresenter(this, repo);

        // Fetch initial data
        presenter.getCategories();
        presenter.getRandomMeals(5);
        presenter.getCountries();  // Fetch countries
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Setup random meals RecyclerView
        RecyclerView mealRecyclerView = view.findViewById(R.id.recyclerRandomMeal);
        mealAdapter = new RandomMealAdapter(getContext(), randomMeals, this);
        mealRecyclerView.setNestedScrollingEnabled(false); // Disable nested scrolling
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mealRecyclerView.setAdapter(mealAdapter);

        // Setup categories RecyclerView
        RecyclerView categoryRecyclerView = view.findViewById(R.id.recyclerCategory);
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        categoryRecyclerView.setNestedScrollingEnabled(false); // Disable nested scrolling
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoryAdapter);

        // Setup countries RecyclerView
        RecyclerView countryRecyclerView = view.findViewById(R.id.recyclerCountry);
        countryAdapter = new ByCountryAdapter(getContext(), countries, this);
        countryRecyclerView.setNestedScrollingEnabled(false);
        countryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
        countryRecyclerView.setAdapter(countryAdapter);

        return view;
    }

    // Update random meals RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void getRandMeal(List<Meal> meals) {
        this.randomMeals.clear();
        this.randomMeals.addAll(meals);
        mealAdapter.notifyDataSetChanged();
    }

    // Update categories RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void getCategories(List<CategoryFilter> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        categoryAdapter.notifyDataSetChanged();
    }

    // Update countries RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void getCountry(List<CountryFilter> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getError(String errMsg) {
        if (getContext() != null) {
            Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
        }
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
        } else {
            Toast.makeText(getContext(), "Meal ID is missing", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle country click events
    @Override
    public void onAreaClicked(CountryFilter country) {
        // Navigate to FilteredItemFragment to display meals by country
        Bundle bundle = new Bundle();
        bundle.putString("selectedArea", country.getStrArea());

        FilteredItemFragment filteredItemFragment = new FilteredItemFragment();
        filteredItemFragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentNav, filteredItemFragment)
                .addToBackStack(null)
                .commit();
    }
}


