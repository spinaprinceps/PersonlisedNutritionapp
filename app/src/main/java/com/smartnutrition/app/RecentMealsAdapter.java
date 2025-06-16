package com.smartnutrition.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecentMealsAdapter extends RecyclerView.Adapter<RecentMealsAdapter.MealViewHolder> {
    private final List<Meal> meals;

    public RecentMealsAdapter(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_recent_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.mealTypeText.setText(meal.getType());
        holder.mealDescriptionText.setText(meal.getDescription());
        holder.mealCaloriesText.setText(meal.getCalories());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView mealTypeText;
        TextView mealDescriptionText;
        TextView mealCaloriesText;

        MealViewHolder(View itemView) {
            super(itemView);
            mealTypeText = itemView.findViewById(R.id.mealTypeText);
            mealDescriptionText = itemView.findViewById(R.id.mealDescriptionText);
            mealCaloriesText = itemView.findViewById(R.id.mealCaloriesText);
        }
    }
} 