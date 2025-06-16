package com.smartnutrition.app;

public class Meal {
    private String type;
    private String description;
    private String calories;

    public Meal(String type, String description, String calories) {
        this.type = type;
        this.description = description;
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
} 