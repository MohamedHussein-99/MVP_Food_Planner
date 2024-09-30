package com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.mvp_food_planner.Screens.FilterScreen.View.Searchable;

import java.util.ArrayList;
import java.util.List;


public class ByCategoryFragment extends Fragment implements CategoryView {

    private RecyclerView recyclerCategory;
    private ByCategoryPresenter presenter;
    private ByCategoryAdapter adapter;
    private List<CategoryFilter> categories = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_by_category, container, false);

        recyclerCategory = view.findViewById(R.id.recyclerCategoryfilter);
        recyclerCategory.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Grid with 2 columns

        adapter = new ByCategoryAdapter(getContext(), categories);
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
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }
}


