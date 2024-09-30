package com.example.mvp_food_planner.Screens.FilterScreen.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mvp_food_planner.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class FilterFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    //private EditText searchBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
       // searchBar = view.findViewById(R.id.searchBar);

        // Set up ViewPagerAdapter and ViewPager
        viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();

            }
        });

//        // Link TabLayout with ViewPager2
//        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
//            switch (position) {
//                case 0:
//                    tab.setText("Category");
//                    break;
//                case 1:
//                    tab.setText("Ingredient");
//                    break;
//                case 2:
//                    tab.setText("Country");
//                    break;
//
//            }
//        }).attach();

        /*
        // Handle search functionality
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Fragment currentFragment = getCurrentFragment();
                if (currentFragment instanceof Searchable) {
                    ((Searchable) currentFragment).onSearchQuery(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

         */


        return view;
    }

    private Fragment getCurrentFragment() {
        return getChildFragmentManager().findFragmentByTag("f" + viewPager.getCurrentItem());
    }

}