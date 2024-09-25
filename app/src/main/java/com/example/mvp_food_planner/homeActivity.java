package com.example.mvp_food_planner;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvp_food_planner.View.FavFragment;
import com.example.mvp_food_planner.View.HomeFragment;
import com.example.mvp_food_planner.View.plannerFragment;
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
                replaceFragment(new plannerFragment());
            } else {
                return false; // If no match, return false
            }
            return true; // Return true if an item was selected
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }
}