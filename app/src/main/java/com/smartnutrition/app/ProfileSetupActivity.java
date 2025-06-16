package com.smartnutrition.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileSetupActivity extends AppCompatActivity {
    private static final String TAG = "ProfileSetupActivity";
    private TextInputEditText nameEditText;
    private TextInputEditText ageEditText;
    private TextInputEditText heightEditText;
    private TextInputEditText weightEditText;
    private AutoCompleteTextView genderAutoComplete;
    private AutoCompleteTextView dietaryPreferenceAutoComplete;
    private AutoCompleteTextView regionalPreferenceAutoComplete;
    private AutoCompleteTextView healthConditionsAutoComplete;
    private MaterialButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile_setup);

            setupToolbar();
            initializeViews();
            setupDropdowns();
            setupClickListeners();
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
            Toast.makeText(this, "Error initializing profile setup", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupToolbar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("Profile Setup");
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up toolbar: " + e.getMessage());
        }
    }

    private void initializeViews() {
        try {
            nameEditText = findViewById(R.id.nameEditText);
            ageEditText = findViewById(R.id.ageEditText);
            heightEditText = findViewById(R.id.heightEditText);
            weightEditText = findViewById(R.id.weightEditText);
            genderAutoComplete = findViewById(R.id.genderAutoComplete);
            dietaryPreferenceAutoComplete = findViewById(R.id.dietaryPreferenceAutoComplete);
            regionalPreferenceAutoComplete = findViewById(R.id.regionalPreferenceAutoComplete);
            healthConditionsAutoComplete = findViewById(R.id.healthConditionsAutoComplete);
            saveButton = findViewById(R.id.saveButton);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing views: " + e.getMessage());
        }
    }

    private void setupDropdowns() {
        try {
            // Gender options
            String[] genderOptions = {"Male", "Female", "Other"};
            ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, genderOptions);
            genderAutoComplete.setAdapter(genderAdapter);

            // Dietary preference options
            String[] dietaryOptions = {"Vegetarian", "Vegan", "Non-vegetarian", "Pescatarian"};
            ArrayAdapter<String> dietaryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, dietaryOptions);
            dietaryPreferenceAutoComplete.setAdapter(dietaryAdapter);

            // Regional preference options
            String[] regionalOptions = {"North Indian", "South Indian", "East Indian", "West Indian", "All"};
            ArrayAdapter<String> regionalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, regionalOptions);
            regionalPreferenceAutoComplete.setAdapter(regionalAdapter);

            // Health conditions options
            String[] healthOptions = {"None", "Diabetes", "Hypertension", "Heart Disease", "Other"};
            ArrayAdapter<String> healthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, healthOptions);
            healthConditionsAutoComplete.setAdapter(healthAdapter);
        } catch (Exception e) {
            Log.e(TAG, "Error setting up dropdowns: " + e.getMessage());
        }
    }

    private void setupClickListeners() {
        try {
            if (saveButton != null) {
                saveButton.setOnClickListener(v -> {
                    try {
                        if (validateInputs()) {
                            // Skip database save and go directly to dashboard
                            startActivity(new Intent(ProfileSetupActivity.this, DashboardActivity.class));
                            finish();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Error in save button click: " + e.getMessage());
                        Toast.makeText(ProfileSetupActivity.this, "Error saving profile", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up click listeners: " + e.getMessage());
        }
    }

    private boolean validateInputs() {
        try {
            if (nameEditText == null || ageEditText == null || heightEditText == null || 
                weightEditText == null || genderAutoComplete == null || 
                dietaryPreferenceAutoComplete == null || regionalPreferenceAutoComplete == null) {
                return false;
            }

            if (nameEditText.getText().toString().isEmpty() ||
                ageEditText.getText().toString().isEmpty() ||
                heightEditText.getText().toString().isEmpty() ||
                weightEditText.getText().toString().isEmpty() ||
                genderAutoComplete.getText().toString().isEmpty() ||
                dietaryPreferenceAutoComplete.getText().toString().isEmpty() ||
                regionalPreferenceAutoComplete.getText().toString().isEmpty()) {
                
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error validating inputs: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try {
            if (item.getItemId() == android.R.id.home) {
                onBackPressed();
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in options item selected: " + e.getMessage());
        }
        return super.onOptionsItemSelected(item);
    }
} 