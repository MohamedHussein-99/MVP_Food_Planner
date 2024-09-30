package com.example.mvp_food_planner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvp_food_planner.Screens.FavScreen.View.FavFragment;
import com.example.mvp_food_planner.Screens.FilterScreen.View.FilterFragment;
import com.example.mvp_food_planner.Screens.HomeScreen.View.HomeFragment;
import com.example.mvp_food_planner.Screens.PlannerScreen.View.PlannerFragment;
import com.example.mvp_food_planner.databinding.ActivityHomeBinding;

public class homeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment()); // set the default fragment for start

        // set listener for the button
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.HomeMenu) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.FavMenu) {
                replaceFragment(new FavFragment());
            } else if (item.getItemId() == R.id.PlannerMenu) {
                replaceFragment(new PlannerFragment());
            } else if (item.getItemId() == R.id.FilterMenu){
                replaceFragment(new FilterFragment()); // Filter
            } else {
                return false; // If no match, return false
            }
            return true; // Return true if an item was selected
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentNav, fragment);
        fragmentTransaction.commit();
    }
}