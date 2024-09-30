package com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.Presenter;

import com.example.mvp_food_planner.Model.POJO.CountryFilter;
import com.example.mvp_food_planner.Model.Repo.Repo;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View.ByCountryView;
import com.example.mvp_food_planner.Network.NetworkCallback;

import java.util.List;
import java.util.stream.Collectors;

public class ByCountryPresenter {
//    private final ByCountryView view;
//    private final Repo repository;
//
//    public ByCountryPresenter(ByCountryView view, Repo repository) {
//        this.view = view;
//        this.repository = repository;
//    }
//
//    public void loadCountries(String query) {
//        repository.fetchCountries(new NetworkCallback<CountryFilter>() {
//            @Override
//            public void onSuccess(List<CountryFilter> countries) {
//                // If search query is provided, filter the countries
//                if (query != null && !query.isEmpty()) {
//                    List<CountryFilter> filteredCountries = countries.stream()
//                            .filter(country -> country.getName().toLowerCase().contains(query.toLowerCase()))
//                            .collect(Collectors.toList()); // Collect filtered countries
//                    view.showCountries(filteredCountries);
//                } else {
//                    // No query, show all countries
//                    view.showCountries(countries);
//                }
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                view.showError(errorMessage);
//            }
//        });
//    }
}

