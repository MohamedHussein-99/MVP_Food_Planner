package com.example.mvp_food_planner.Screens.FilterScreen.ByIngredients.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvp_food_planner.Model.POJO.IngredientFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.FilterScreen.ByIngredients.Presenter.ByIngredientPresenter;
import com.example.mvp_food_planner.Screens.FilterScreen.View.Searchable;

import java.util.ArrayList;
import java.util.List;


public class ByIngredientFragment extends Fragment implements IngredientView , Searchable {

    private ByIngredientPresenter presenter;
    private ByIngredientAdapter ingredientAdapter;
    private List<IngredientFilter> ingredients = new ArrayList<>();

    public ByIngredientFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Repo with Client.getInstance()
        Repo repo = new Repo(Client.getInstance());
        presenter = new ByIngredientPresenter(this, repo);  // Initialize presenter
        presenter.getIngredients();  // Fetch ingredients
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_by_ingredient, container, false);

        // Setup RecyclerView with GridLayoutManager
        RecyclerView recyclerView = view.findViewById(R.id.recyclerIngredient);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));  // 3 columns in grid
        ingredientAdapter = new ByIngredientAdapter(getContext(), ingredients);
        recyclerView.setAdapter(ingredientAdapter);

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showIngredients(List<IngredientFilter> ingredientList) {
        ingredients.clear();
        ingredients.addAll(ingredientList);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchQuery(String query) {

    }
}

