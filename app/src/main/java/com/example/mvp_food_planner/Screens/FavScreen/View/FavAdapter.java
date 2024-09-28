package com.example.mvp_food_planner.Screens.FavScreen.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    private FavoriteListener listener;
   // private RandomMealAdapter.MealClickListener mealClickListener;

    public FavAdapter(List<Meal> meals, Context context, FavoriteListener listener) {
        this.meals = meals;
        this.context = context;
        this.listener = listener;

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
        holder.cbHeart2.setChecked(true); // set the checkbox to checked by default
        holder.cbHeart2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {  // Ensure listener is not null
                if (!isChecked) {
                    listener.onRemoveFromSaved(meal);  // Notify the listener to remove the meal
                }
            }
        });
        // Set the listener for item clicks (navigate to meal details)
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMealClicked(meal);  // Notify listener to display meal details
            }
        });

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
        private CheckBox cbHeart2;


        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            txtfavtitle = itemView.findViewById(R.id.txtfavtitle);
            txtfavcat = itemView.findViewById(R.id.txtfavcat);
            imgfav = itemView.findViewById(R.id.imgfav);
            cbHeart2 = itemView.findViewById(R.id.cbHeart2);
        }
    }
    public void updateData(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }
}
