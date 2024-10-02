package com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.View;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.FilterScreen.FilteredItems.Presenter.FilteredItemPresenter;
import com.example.mvp_food_planner.Screens.MealDetailsScreen.View.MealDetailsFragment;

import java.util.ArrayList;
import java.util.List;


public class FilteredItemFragment extends Fragment implements FilteredItemView, FilteredItemAdapter.MealClickListener {

    private TextView txtItem;
    private RecyclerView recyclerItem;
    private FilteredItemAdapter adapter;
    private FilteredItemPresenter presenter;
    private List<Meal> meals = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filtered_item, container, false);

        txtItem = view.findViewById(R.id.txtItem);
        recyclerItem = view.findViewById(R.id.recyclerItem);
        recyclerItem.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new FilteredItemAdapter(getContext(), meals,this::onMealClicked);
        recyclerItem.setAdapter(adapter);

        presenter = new FilteredItemPresenter(this , new Repo(Client.getInstance()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            String selectedCategory = bundle.getString("selectedCategory");
            String selectedArea = bundle.getString("selectedArea");
            String selectedIngredient = bundle.getString("selectedIngredient");

            if (selectedCategory != null) {
                txtItem.setText("Your Favorite " + selectedCategory+ " Dishes" );
                presenter.fetchMealsByCategory(selectedCategory);
            } else if (selectedArea != null) {
                txtItem.setText("Lovely " + selectedArea + " Cuisine");
                presenter.fetchMealsByArea(selectedArea);
            } else if (selectedIngredient != null) {
                txtItem.setText("Made With " + selectedIngredient);
                presenter.fetchMealsByIngredient(selectedIngredient);

            }
        }
        return view;
    }

    @Override
    public void showMealList(List<Meal> randomList) {
        recyclerItem.setVisibility(View.VISIBLE);
        meals.clear();
        meals.addAll(randomList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.i(TAG, "showError: " + error);
    }

    @Override
    public void onMealClicked(Meal meal) {
        Bundle bundle = new Bundle();
        bundle.putString("mealId", meal.idMeal);
        MealDetailsFragment detailsFragment = new MealDetailsFragment();
        detailsFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentNav, detailsFragment)
                .addToBackStack(null)
                .commit();
    }

    public void reloadData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String selectedCategory = bundle.getString("selectedCategory");
            String selectedArea = bundle.getString("selectedArea");

            if (selectedCategory != null) {
                presenter.fetchMealsByCategory(selectedCategory);
            } else if (selectedArea != null) {
                presenter.fetchMealsByArea(selectedArea);
            }
        }
    }
}