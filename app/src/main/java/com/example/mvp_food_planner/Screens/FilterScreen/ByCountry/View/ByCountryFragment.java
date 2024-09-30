package com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Network.Client;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.Presenter.ByCountryPresenter;
import com.example.mvp_food_planner.Screens.FilterScreen.View.Searchable;

import java.util.ArrayList;
import java.util.List;


public class ByCountryFragment extends Fragment  {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_by_country, container, false);
    }


}