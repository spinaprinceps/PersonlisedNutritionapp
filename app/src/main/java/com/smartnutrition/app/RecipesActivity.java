package com.smartnutrition.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecipesActivity extends AppCompatActivity {
    private RecyclerView recipesRecyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipes;
    private ExecutorService executorService;
    private Handler mainHandler;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // Initialize thread pool and handler
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Recipes");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize views
        initializeViews();
        
        // Load recipes
        loadRecipes();

        // Set up bottom navigation
        setupBottomNavigation();
    }

    private void initializeViews() {
        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        
        recipes = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(recipes);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipesRecyclerView.setAdapter(recipeAdapter);
    }

    private void loadRecipes() {
        executorService.execute(() -> {
            try {
                // Simulate loading recipes
                Thread.sleep(100);
                
                List<Recipe> recipeList = new ArrayList<>();
                recipeList.add(new Recipe(
                    "Healthy Breakfast Bowl",
                    "A nutritious breakfast bowl with oats, fruits, and nuts",
                    "300 calories",
                    "Breakfast",
                    "https://example.com/breakfast_bowl.jpg"
                ));
                recipeList.add(new Recipe(
                    "Grilled Chicken Salad",
                    "Fresh salad with grilled chicken breast and vegetables",
                    "450 calories",
                    "Lunch",
                    "https://example.com/chicken_salad.jpg"
                ));
                recipeList.add(new Recipe(
                    "Salmon with Vegetables",
                    "Baked salmon with roasted vegetables",
                    "550 calories",
                    "Dinner",
                    "https://example.com/salmon.jpg"
                ));
                
                mainHandler.post(() -> {
                    recipes.clear();
                    recipes.addAll(recipeList);
                    recipeAdapter.notifyDataSetChanged();
                });
            } catch (Exception e) {
                mainHandler.post(() -> {
                    Toast.makeText(RecipesActivity.this, 
                        "Error loading recipes: " + e.getMessage(), 
                        Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new android.content.Intent(RecipesActivity.this, ProfileActivity.class));
                return true;
            } else if (itemId == R.id.nav_settings) {
                startActivity(new android.content.Intent(RecipesActivity.this, SettingsActivity.class));
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
} 