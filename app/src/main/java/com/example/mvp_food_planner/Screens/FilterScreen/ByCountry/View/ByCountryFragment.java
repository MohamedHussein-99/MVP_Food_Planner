package com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.Presenter.ByCountryPresenter;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.Presenter.FilteredItemPresenter;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View.FilteredItemFragment;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View.FilteredItemView;
import com.example.mvp_food_planner.Screens.FilterScreen.View.Searchable;

import java.util.ArrayList;
import java.util.List;


public class ByCountryFragment extends Fragment implements ByCountryAdapter.AreaClickListener, ByCountryView {

    private RecyclerView recyclerView;
    private ByCountryAdapter adapter;
    private List<CountryFilter> areaList = new ArrayList<>();
    private List<CountryFilter> filteredList = new ArrayList<>();
    private ByCountryPresenter presenter;
    private SearchView searchBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_by_country, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCountry);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        searchBar = view.findViewById(R.id.searchBar);  // Find the SearchView
        adapter = new ByCountryAdapter(getContext(), filteredList, this);
        recyclerView.setAdapter(adapter);

        presenter = new ByCountryPresenter(this, new Repo(Client.getInstance()));
        presenter.fetchAreas();  // Fetch the data

        // Set up search listener
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterCountryList(newText);  // Handle text changes , for live search
                return true;
            }
        });

        return view;
    }

    // Filter country list based on search query
    private void filterCountryList(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(areaList);  // Show all items if the search query is empty
        } else {
            for (CountryFilter country : areaList) {
                if (country.getStrArea().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(country);
                }
            }
        }
        adapter.notifyDataSetChanged();  // Refresh the RecyclerView
    }

    @Override
    public void onAreaClicked(CountryFilter area) {
        // Navigate to FilteredItemFragment and filter meals by country
        Bundle bundle = new Bundle();
        bundle.putString("selectedArea", area.getStrArea());

        FilteredItemFragment filteredItemFragment = new FilteredItemFragment();
        filteredItemFragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentNav, filteredItemFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showCountries(List<CountryFilter> areas) {
        areaList.clear();
        areaList.addAll(areas);
        filteredList.clear();
        filteredList.addAll(areas);  // Initially, filtered list is the same as the full list
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMealList(List<Meal> meals) {
        // Not needed for this fragment
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}




