package com.smartnutrition.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.chip.Chip;

import java.util.List;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes != null ? recipes : new ArrayList<>();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes != null ? recipes : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        if (recipe == null) return;

        // Set text fields with null checks
        holder.nameText.setText(recipe.getName() != null ? recipe.getName() : "");
        holder.descriptionText.setText(recipe.getDescription() != null ? recipe.getDescription() : "");
        holder.caloriesText.setText(recipe.getCalories() != null ? recipe.getCalories() : "");
        holder.mealTypeChip.setText(recipe.getMealType() != null ? recipe.getMealType() : "");

        // Load recipe image using Glide with error handling
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.placeholder_food)
                .error(R.drawable.placeholder_food)
                .centerCrop();

        Glide.with(holder.itemView.getContext())
                .load(recipe.getImageUrl())
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.recipeImage);
    }

    @Override
    public int getItemCount() {
        return recipes != null ? recipes.size() : 0;
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImage;
        TextView nameText;
        TextView descriptionText;
        TextView caloriesText;
        Chip mealTypeChip;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            nameText = itemView.findViewById(R.id.recipeName);
            descriptionText = itemView.findViewById(R.id.recipeDescription);
            caloriesText = itemView.findViewById(R.id.recipeCalories);
            mealTypeChip = itemView.findViewById(R.id.mealTypeChip);

            if (recipeImage == null || nameText == null || 
                descriptionText == null || caloriesText == null || 
                mealTypeChip == null) {
                throw new IllegalStateException("Required views not found in item layout");
            }
        }
    }
} 