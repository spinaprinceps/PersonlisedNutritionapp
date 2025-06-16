package com.smartnutrition.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";
    private Handler mainHandler;
    private MaterialCardView dietCard;
    private MaterialCardView foodAnalysisCard;
    private MaterialCardView recipesCard;
    private MaterialCardView nutritionCard;
    private TextView welcomeText;
    private RecyclerView recentMealsRecyclerView;
    private RecentMealsAdapter recentMealsAdapter;
    private List<Meal> recentMeals;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);

            // Initialize handler for UI operations
            mainHandler = new Handler(Looper.getMainLooper());

            // Set up toolbar
            setupToolbar();

            // Initialize views
            initializeViews();

            // Set up click listeners
            setupClickListeners();

            // Initialize recent meals
            initializeRecentMeals();

            // Set up bottom navigation
            setupBottomNavigation();

        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
        }
    }

    private void setupToolbar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Smart Nutrition");
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up toolbar: " + e.getMessage());
        }
    }

    private void initializeViews() {
        try {
            dietCard = findViewById(R.id.dietCard);
            foodAnalysisCard = findViewById(R.id.foodAnalysisCard);
            recipesCard = findViewById(R.id.recipesCard);
            nutritionCard = findViewById(R.id.nutritionCard);
            welcomeText = findViewById(R.id.welcomeText);
            recentMealsRecyclerView = findViewById(R.id.recentMealsRecyclerView);
            bottomNavigation = findViewById(R.id.bottomNavigation);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing views: " + e.getMessage());
        }
    }

    private void setupClickListeners() {
        try {
            if (dietCard != null) {
                dietCard.setOnClickListener(v -> {
                    try {
                        startActivity(new Intent(DashboardActivity.this, DietRecommendationsActivity.class));
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting DietRecommendationsActivity: " + e.getMessage());
                    }
                });
            }

            if (foodAnalysisCard != null) {
                foodAnalysisCard.setOnClickListener(v -> {
                    try {
                        startActivity(new Intent(DashboardActivity.this, FoodAnalysisActivity.class));
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting FoodAnalysisActivity: " + e.getMessage());
                    }
                });
            }

            if (recipesCard != null) {
                recipesCard.setOnClickListener(v -> {
                    try {
                        startActivity(new Intent(DashboardActivity.this, RecipesActivity.class));
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting RecipesActivity: " + e.getMessage());
                    }
                });
            }

            if (nutritionCard != null) {
                nutritionCard.setOnClickListener(v -> {
                    try {
                        startActivity(new Intent(DashboardActivity.this, NutritionOverviewActivity.class));
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting NutritionOverviewActivity: " + e.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up click listeners: " + e.getMessage());
        }
    }

    private void initializeRecentMeals() {
        try {
            if (recentMealsRecyclerView != null) {
                recentMeals = new ArrayList<>();
                recentMealsAdapter = new RecentMealsAdapter(recentMeals);
                recentMealsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                recentMealsRecyclerView.setAdapter(recentMealsAdapter);

                // Add sample meals
                recentMeals.add(new Meal("Breakfast", "Oatmeal with fruits", "300 calories"));
                recentMeals.add(new Meal("Lunch", "Grilled chicken salad", "450 calories"));
                recentMeals.add(new Meal("Dinner", "Salmon with vegetables", "550 calories"));
                recentMealsAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error initializing recent meals: " + e.getMessage());
        }
    }

    private void setupBottomNavigation() {
        try {
            if (bottomNavigation != null) {
                bottomNavigation.setOnItemSelectedListener(item -> {
                    try {
                        int itemId = item.getItemId();
                        if (itemId == R.id.nav_home) {
                            // Already on home
                            return true;
                        } else if (itemId == R.id.nav_profile) {
                            startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                            return true;
                        } else if (itemId == R.id.nav_settings) {
                            startActivity(new Intent(DashboardActivity.this, SettingsActivity.class));
                            return true;
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Error in bottom navigation click: " + e.getMessage());
                    }
                    return false;
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up bottom navigation: " + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainHandler != null) {
            mainHandler.removeCallbacksAndMessages(null);
        }
    }
} 