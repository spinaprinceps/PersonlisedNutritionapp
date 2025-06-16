package com.smartnutrition.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FoodAnalysisActivity extends AppCompatActivity {
    private TextView foodNameText;
    private TextView caloriesText;
    private TextView proteinText;
    private TextView carbsText;
    private TextView fatText;
    private TextView vitaminsText;
    private TextView mineralsText;
    private BottomNavigationView bottomNavigation;
    private ExecutorService executorService;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analysis);

        // Initialize thread pool and handler
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Food Analysis");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize views
        initializeViews();
        
        // Load food analysis data
        loadFoodAnalysisData();

        // Set up bottom navigation
        setupBottomNavigation();
    }

    private void initializeViews() {
        foodNameText = findViewById(R.id.foodNameText);
        caloriesText = findViewById(R.id.caloriesText);
        proteinText = findViewById(R.id.proteinText);
        carbsText = findViewById(R.id.carbsText);
        fatText = findViewById(R.id.fatText);
        vitaminsText = findViewById(R.id.vitaminsText);
        mineralsText = findViewById(R.id.mineralsText);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void loadFoodAnalysisData() {
        executorService.execute(() -> {
            try {
                // Simulate loading food analysis data
                Thread.sleep(100);
                
                mainHandler.post(() -> {
                    // Update UI with food analysis data
                    foodNameText.setText("Grilled Chicken Breast");
                    caloriesText.setText("165 calories per 100g");
                    proteinText.setText("31g protein per 100g");
                    carbsText.setText("0g carbs per 100g");
                    fatText.setText("3.6g fat per 100g");
                    vitaminsText.setText("Rich in B vitamins (B6, B12)");
                    mineralsText.setText("Good source of selenium and phosphorus");
                });
            } catch (Exception e) {
                mainHandler.post(() -> {
                    Toast.makeText(FoodAnalysisActivity.this,
                        "Error loading food analysis data: " + e.getMessage(), 
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
                startActivity(new Intent(FoodAnalysisActivity.this, ProfileActivity.class));
                return true;
            } else if (itemId == R.id.nav_settings) {
                startActivity(new Intent(FoodAnalysisActivity.this, SettingsActivity.class));
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