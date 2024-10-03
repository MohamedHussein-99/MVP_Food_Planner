package com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CategoryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.Presenter.ByCategoryPresenter;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View.FilteredItemFragment;
import com.example.mvp_food_planner.Screens.FilterScreen.View.Searchable;

import java.util.ArrayList;
import java.util.List;


public class ByCategoryFragment extends Fragment implements CategoryView {

    private RecyclerView recyclerCategory;
    private ByCategoryPresenter presenter;
    private ByCategoryAdapter adapter;
    private List<CategoryFilter> categories = new ArrayList<>();
    private List<CategoryFilter> filteredCategories = new ArrayList<>(); // For filtering
    private SearchView searchView; // Add SearchView reference

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_by_category, container, false);

        recyclerCategory = view.findViewById(R.id.recyclerCategoryfilter);
        recyclerCategory.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Grid with 2 columns

        // Initialize SearchView
        searchView = view.findViewById(R.id.searchBar); // Make sure this matches the search bar ID
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCategories(newText); // Filter categories as user types
                return true;
            }
        });

        // Set the click listener for categories
        adapter = new ByCategoryAdapter(getContext(), filteredCategories, category -> {
            // Navigate to FilteredItemFragment and pass the selected category
            Bundle bundle = new Bundle();
            bundle.putString("selectedCategory", category);
            FilteredItemFragment filteredItemFragment = new FilteredItemFragment();
            filteredItemFragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentNav, filteredItemFragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerCategory.setAdapter(adapter);

        // Initialize presenter
        presenter = new ByCategoryPresenter(this, new Repo(Client.getInstance()));

        // Fetch categories
        presenter.getCategories();

        return view;
    }

    @Override
    public void displayCategories(List<CategoryFilter> categoryList) {
        categories.clear();
        categories.addAll(categoryList);
        filteredCategories.clear();
        filteredCategories.addAll(categoryList); // Initially show all categories
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    // Method to filter categories based on search query
    private void filterCategories(String query) {
        filteredCategories.clear();
        if (query.isEmpty()) {
            filteredCategories.addAll(categories); // If no search query, show all categories
        } else {
            for (CategoryFilter category : categories) {
                if (category.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
                    filteredCategories.add(category);
                }
            }
        }
        adapter.notifyDataSetChanged(); // Refresh the adapter
    }
}





