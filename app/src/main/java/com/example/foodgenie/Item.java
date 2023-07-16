package com.example.foodgenie;

import android.widget.Button;
import android.widget.TextView;

public class Item {

    private String foodImage, foodName, foodLabels, foodServings, foodTotalCalories,
            foodProtein, foodFat, foodCarb, foodCholesterol, foodSodium,
            foodCalcium, foodMagnesium, foodPotassium, foodIron, foodSeeRecipe;

    public Item(String foodImage, String foodName, String foodProtein) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.foodProtein = foodProtein;

    }

    public Item(String foodImage, String foodName, String foodLabels, String foodServings, String foodTotalCalories, String foodProtein, String foodFat, String foodCarb, String foodCholesterol, String foodSodium, String foodCalcium, String foodMagnesium, String foodPotassium, String foodIron, String foodSeeRecipe) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.foodLabels = foodLabels;
        this.foodServings = foodServings;
        this.foodTotalCalories = foodTotalCalories;
        this.foodProtein = foodProtein;
        this.foodFat = foodFat;
        this.foodCarb = foodCarb;
        this.foodCholesterol = foodCholesterol;
        this.foodSodium = foodSodium;
        this.foodCalcium = foodCalcium;
        this.foodMagnesium = foodMagnesium;
        this.foodPotassium = foodPotassium;
        this.foodIron = foodIron;
        this.foodSeeRecipe = foodSeeRecipe;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodLabels() {
        return foodLabels;
    }

    public String getFoodServings() {
        return foodServings;
    }

    public String getFoodTotalCalories() {
        return foodTotalCalories;
    }

    public String getFoodProtein() {
        return foodProtein;
    }

    public String getFoodFat() {
        return foodFat;
    }

    public String getFoodCarb() {
        return foodCarb;
    }

    public String getFoodCholesterol() {
        return foodCholesterol;
    }

    public String getFoodSodium() {
        return foodSodium;
    }

    public String getFoodCalcium() {
        return foodCalcium;
    }

    public String getFoodMagnesium() {
        return foodMagnesium;
    }

    public String getFoodPotassium() {
        return foodPotassium;
    }

    public String getFoodIron() {
        return foodIron;
    }

    public String getFoodSeeRecipe() {
        return foodSeeRecipe;
    }
}
