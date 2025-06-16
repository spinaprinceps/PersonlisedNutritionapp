package com.smartnutrition.app;

public class Recipe {
    private String name;
    private String description;
    private String calories;
    private String mealType;
    private String imageUrl;

    public Recipe(String name, String description, String calories, String mealType, String imageUrl) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.mealType = mealType;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCalories() {
        return calories;
    }

    public String getMealType() {
        return mealType;
    }

    public String getImageUrl() {
        return imageUrl;
    }
} 