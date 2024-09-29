package com.example.mvp_food_planner.Screens.PlannerScreen.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.mvp_food_planner.Model.Entity.PlannedMeal;
import com.example.mvp_food_planner.Model.Repo.MealLocalRepository;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.PlannerScreen.Presenter.PlannerPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PlannerFragment extends Fragment implements PlannerView, PlannerAdapter.PlannerListener {

    private PlannerPresenter presenter;
    private PlannerAdapter adapter;
    private RecyclerView plannedRecycler;
    private CalendarView plannedCalender;
    private List<PlannedMeal> plannedMeals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planner, container, false);

        plannedRecycler = view.findViewById(R.id.plannedRecycler);
        plannedCalender = view.findViewById(R.id.plannedCalender);

        plannedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PlannerAdapter(new ArrayList<>(), getContext(), this);
        plannedRecycler.setAdapter(adapter);

        presenter = new PlannerPresenter(this, new MealLocalRepository(getContext()));

        // Load meals for the current date initially
        plannedCalender.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // Set the time to 00:00:00 to ensure date-only comparison
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date selectedDate = calendar.getTime();
            presenter.loadMealsForDay(selectedDate);
        });


        // Load meals for today by default
        presenter.loadMealsForDay(new Date());

        return view;
    }

    @Override
    public void showPlannedMeals(List<PlannedMeal> meals) {
        this.plannedMeals = meals;
        adapter.updatePlannedMeals(meals);
    }

    @Override
    public void onDeleteMeal(PlannedMeal meal) {
        presenter.deletePlannedMeal(meal);
        Toast.makeText(getContext(), "Meal deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        //Toast.makeText(requireContext(), "Error message", Toast.LENGTH_SHORT).show();

    }
}
