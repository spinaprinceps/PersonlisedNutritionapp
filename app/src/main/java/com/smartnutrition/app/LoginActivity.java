package com.smartnutrition.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton loginButton;
    private MaterialButton signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_login);
            initializeViews();
            setupClickListeners();
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
            Toast.makeText(this, "Error initializing app: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initializeViews() {
        try {
            emailEditText = findViewById(R.id.emailEditText);
            passwordEditText = findViewById(R.id.passwordEditText);
            loginButton = findViewById(R.id.loginButton);
            signUpButton = findViewById(R.id.signUpButton);
        } catch (Exception e) {
            Log.e(TAG, "Error in initializeViews: " + e.getMessage());
            throw e;
        }
    }

    private void setupClickListeners() {
        try {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // Skip authentication and go directly to profile setup
                        startActivity(new Intent(LoginActivity.this, ProfileSetupActivity.class));
                        finish();
                    } catch (Exception e) {
                        Log.e(TAG, "Error in login button click: " + e.getMessage());
                        Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // Skip authentication and go directly to profile setup
                        startActivity(new Intent(LoginActivity.this, ProfileSetupActivity.class));
                        finish();
                    } catch (Exception e) {
                        Log.e(TAG, "Error in signup button click: " + e.getMessage());
                        Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error in setupClickListeners: " + e.getMessage());
            throw e;
        }
    }
} 