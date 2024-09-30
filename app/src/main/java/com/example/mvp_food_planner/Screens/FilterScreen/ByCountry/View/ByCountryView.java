package com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View;

import java.util.List;

public interface ByCountryView {
    void showCountries(List<String> countries);
    void showError(String message);
}
