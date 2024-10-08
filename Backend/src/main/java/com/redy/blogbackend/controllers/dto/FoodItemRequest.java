package com.redy.blogbackend.controllers.dto;

public class FoodItemRequest {
    private String foodItemName;
    private String expiryDate;

    // Getters and Setters
    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
