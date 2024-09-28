package com.example.mvp_food_planner.Screens.FavScreen.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvp_food_planner.Model.Entity.Meal;
import com.example.mvp_food_planner.R;
import com.example.mvp_food_planner.Screens.HomeScreen.View.RandomMealAdapter;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder>  {
    private List<Meal> meals ;
    private Context context;
   // private RandomMealAdapter.MealClickListener mealClickListener;

    public FavAdapter(List<Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
        //this.mealClickListener = listener;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_fav, parent, false);
        return new FavViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.txtfavtitle.setText(meal.getStrMeal());
        holder.txtfavcat.setText(meal.getStrCategory()+" - "+meal.getStrArea());
        Glide.with(context).load(meal.getStrMealThumb()).placeholder(R.drawable.ic_launcher_foreground).into(holder.imgfav);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mealClickListener.onMealClicked(meal);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

//    @Override
//    public void onMealClicked(Meal meal) {
//
//    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        private TextView txtfavtitle , txtfavcat;
        private ImageView imgfav;


        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            txtfavtitle = itemView.findViewById(R.id.txtfavtitle);
            txtfavcat = itemView.findViewById(R.id.txtfavcat);
            imgfav = itemView.findViewById(R.id.imgfav);
        }
    }
    public void updateData(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }
}
