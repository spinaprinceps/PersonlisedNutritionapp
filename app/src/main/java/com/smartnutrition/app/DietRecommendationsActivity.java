package com.smartnutrition.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import java.util.ArrayList;
import java.util.List;

public class DietRecommendationsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ChipGroup filterChipGroup;
    private RecyclerView recipesRecyclerView;
    private CircularProgressIndicator progressIndicator;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_recommendations);

        try {
            // Initialize views
            initializeViews();
            
            // Set up toolbar
            setupToolbar();
            
            // Set up recycler view
            setupRecyclerView();
            
            // Set up chip group
            setupChipGroup();
            
            // Load recipes
            loadRecipes();
        } catch (Exception e) {
            Toast.makeText(this, "Error initializing activity: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        filterChipGroup = findViewById(R.id.filterChipGroup);
        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);
        progressIndicator = findViewById(R.id.progressIndicator);

        if (toolbar == null || filterChipGroup == null || recipesRecyclerView == null || progressIndicator == null) {
            throw new IllegalStateException("Required views not found in layout");
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Indian Diet Recommendations");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupRecyclerView() {
        recipes = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(recipes);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipesRecyclerView.setAdapter(recipeAdapter);
    }

    private void setupChipGroup() {
        filterChipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == View.NO_ID) return;
            
            String selectedCategory = "All";
            if (checkedId == R.id.breakfastChip) {
                selectedCategory = "Breakfast";
            } else if (checkedId == R.id.lunchChip) {
                selectedCategory = "Lunch";
            } else if (checkedId == R.id.dinnerChip) {
                selectedCategory = "Dinner";
            } else if (checkedId == R.id.snacksChip) {
                selectedCategory = "Snack";
            } else if (checkedId == R.id.soupChip) {
                selectedCategory = "Soup";
            }
            filterRecipes(selectedCategory);
        });
    }

    private void filterRecipes(String category) {
        if (recipes == null) return;
        
        if (category.equals("All")) {
            recipeAdapter.setRecipes(recipes);
        } else {
            List<Recipe> filteredList = new ArrayList<>();
            for (Recipe recipe : recipes) {
                if (recipe != null && recipe.getMealType() != null && 
                    recipe.getMealType().equals(category)) {
                    filteredList.add(recipe);
                }
            }
            recipeAdapter.setRecipes(filteredList);
        }
    }

    private void loadRecipes() {
        // Show progress
        progressIndicator.setVisibility(View.VISIBLE);
        
        // Simulate loading recipes
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate network delay
                
                // Add Indian diet recipes
                List<Recipe> sampleRecipes = new ArrayList<>();
                
                // Breakfast options
                sampleRecipes.add(new Recipe(
                    "Poha with Vegetables",
                    "Light and nutritious flattened rice with vegetables and peanuts",
                    "350 calories",
                    "Breakfast",
                    "https://example.com/poha.jpg"
                ));
                
                sampleRecipes.add(new Recipe(
                    "Idli with Sambar",
                    "Steamed rice cakes with lentil soup and coconut chutney",
                    "300 calories",
                    "Breakfast",
                    "https://example.com/idli.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Upma",
                    "Semolina cooked with vegetables and spices",
                    "320 calories",
                    "Breakfast",
                    "https://example.com/upma.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Dosa with Chutney",
                    "Crispy fermented rice crepe with coconut chutney",
                    "280 calories",
                    "Breakfast",
                    "https://example.com/dosa.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Besan Chilla",
                    "Protein-rich gram flour pancakes with vegetables",
                    "250 calories",
                    "Breakfast",
                    "https://example.com/chilla.jpg"
                ));
                
                // Lunch options
                sampleRecipes.add(new Recipe(
                    "Dal Chawal with Roti",
                    "Yellow dal with brown rice and whole wheat roti",
                    "450 calories",
                    "Lunch",
                    "https://example.com/dal-chawal.jpg"
                ));
                
                sampleRecipes.add(new Recipe(
                    "Vegetable Pulao",
                    "Spiced rice with mixed vegetables and herbs",
                    "400 calories",
                    "Lunch",
                    "https://example.com/pulao.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Rajma Chawal",
                    "Kidney beans curry with brown rice",
                    "420 calories",
                    "Lunch",
                    "https://example.com/rajma.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Chana Masala with Roti",
                    "Spiced chickpea curry with whole wheat roti",
                    "380 calories",
                    "Lunch",
                    "https://example.com/chana.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Vegetable Biryani",
                    "Fragrant rice with mixed vegetables and spices",
                    "450 calories",
                    "Lunch",
                    "https://example.com/biryani.jpg"
                ));
                
                // Dinner options
                sampleRecipes.add(new Recipe(
                    "Chapati with Sabzi",
                    "Whole wheat flatbread with seasonal vegetable curry",
                    "380 calories",
                    "Dinner",
                    "https://example.com/chapati-sabzi.jpg"
                ));
                
                sampleRecipes.add(new Recipe(
                    "Khichdi",
                    "Comforting one-pot meal of rice and lentils with vegetables",
                    "420 calories",
                    "Dinner",
                    "https://example.com/khichdi.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Methi Thepla",
                    "Fenugreek flatbread with yogurt",
                    "350 calories",
                    "Dinner",
                    "https://example.com/thepla.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Moong Dal Chilla",
                    "Protein-rich green gram pancakes with vegetables",
                    "300 calories",
                    "Dinner",
                    "https://example.com/moong-chilla.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Vegetable Upma",
                    "Semolina with mixed vegetables and spices",
                    "320 calories",
                    "Dinner",
                    "https://example.com/veg-upma.jpg"
                ));
                
                // Snacks
                sampleRecipes.add(new Recipe(
                    "Sprouts Chaat",
                    "Protein-rich sprouted legumes with vegetables and spices",
                    "200 calories",
                    "Snack",
                    "https://example.com/sprouts-chaat.jpg"
                ));
                
                sampleRecipes.add(new Recipe(
                    "Fruit Chaat",
                    "Seasonal fruits with chaat masala and lemon",
                    "150 calories",
                    "Snack",
                    "https://example.com/fruit-chaat.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Makhana (Fox Nuts)",
                    "Roasted lotus seeds with spices",
                    "100 calories",
                    "Snack",
                    "https://example.com/makhana.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Roasted Chana",
                    "Roasted chickpeas with spices",
                    "120 calories",
                    "Snack",
                    "https://example.com/roasted-chana.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Mixed Nuts",
                    "Assorted nuts with raisins",
                    "180 calories",
                    "Snack",
                    "https://example.com/nuts.jpg"
                ));

                // Soups
                sampleRecipes.add(new Recipe(
                    "Tomato Rasam",
                    "Spiced tomato soup with tamarind",
                    "80 calories",
                    "Soup",
                    "https://example.com/rasam.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Moong Dal Soup",
                    "Yellow lentil soup with vegetables",
                    "120 calories",
                    "Soup",
                    "https://example.com/moong-soup.jpg"
                ));

                sampleRecipes.add(new Recipe(
                    "Vegetable Clear Soup",
                    "Light vegetable broth with herbs",
                    "60 calories",
                    "Soup",
                    "https://example.com/clear-soup.jpg"
                ));
                
                // Update UI on main thread
                runOnUiThread(() -> {
                    try {
                        recipes.clear();
                        recipes.addAll(sampleRecipes);
                        recipeAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(DietRecommendationsActivity.this, 
                            "Error updating recipes: " + e.getMessage(), 
                            Toast.LENGTH_SHORT).show();
                    } finally {
                        progressIndicator.setVisibility(View.GONE);
                    }
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(DietRecommendationsActivity.this, 
                        "Error loading recipes: " + e.getMessage(), 
                        Toast.LENGTH_SHORT).show();
                    progressIndicator.setVisibility(View.GONE);
                });
            }
        }).start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clear references to prevent memory leaks
        recipes = null;
        recipeAdapter = null;
    }
} 