package com.example.mvp_food_planner.Screens.FilterScreen.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mvp_food_planner.Screens.FilterScreen.ByCategory.View.ByCategoryFragment;
import com.example.mvp_food_planner.Screens.FilterScreen.ByCountry.View.ByCountryFragment;
import com.example.mvp_food_planner.Screens.FilterScreen.ByIngredients.View.ByIngredientFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ByCategoryFragment();
            case 1:
                return new ByCountryFragment();
            case 2:
                return new ByIngredientFragment();
            default:
                return new ByCategoryFragment(); // default fragment
        }
    }

    @Override
    public int getItemCount() {
        return 3; // number of fragments Tabs
    }
}
