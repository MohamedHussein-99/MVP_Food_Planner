package com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View;

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
    private ByCountryPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_by_country, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCountry);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Set up the adapter with listener
        adapter = new ByCountryAdapter(getContext(), areaList, this);
        recyclerView.setAdapter(adapter);

        // Fetch areas (countries) through presenter
        presenter = new ByCountryPresenter(this, new Repo(Client.getInstance()));
        presenter.fetchAreas();

        return view;
    }

    @Override
    public void onAreaClicked(CountryFilter area) {
        // When a country is clicked, navigate to FilteredItemFragment and filter meals by country
        Bundle bundle = new Bundle();
        bundle.putString("selectedArea", area.getStrArea());

        FilteredItemFragment filteredItemFragment = new FilteredItemFragment();
        filteredItemFragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentNav, filteredItemFragment)
                .addToBackStack(null)
                .commit();
    }

    // Show the list of areas (countries) in the recycler view
    @Override
    public void showCountries(List<CountryFilter> areas) {
        areaList.clear();
        areaList.addAll(areas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMealList(List<Meal> meals) {
        // If needed, you can implement this method to show meals
    }

    @Override
    public void showError(String message) {
        // Show error to the user (e.g., using a Toast or Snackbar)
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
