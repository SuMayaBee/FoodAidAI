//package com.redy.blogbackend.controllers;
//
//import com.redy.blogbackend.entities.FoodItem;
//import com.redy.blogbackend.services.FoodItemService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor  // Generates constructor for final fields (dependency injection)
//@RequestMapping("/users/food")  // Base URL for food item operations
//public class FoodItemController {
//
//    private final FoodItemService foodItemService;
//
//    // Add a food item (user context is fetched internally)
//    @PostMapping("/add")
//    public FoodItem addFoodItem(@RequestParam String foodItemName, @RequestParam String expiryDate) throws Exception {
//        LocalDate expiry = LocalDate.parse(expiryDate);
//        return foodItemService.addFoodItem(foodItemName, expiry);
//    }
//
//    // Get all food items for the current logged-in user
//    @GetMapping("/list")
//    public List<FoodItem> getFoodItemsForCurrentUser() throws Exception {
//        return foodItemService.getFoodItemsForCurrentUser();
//    }
//
//    // Delete a food item
//    @DeleteMapping("/delete/{foodItemId}")
//    public void deleteFoodItem(@PathVariable Long foodItemId) throws Exception {
//        foodItemService.deleteFoodItem(foodItemId);
//    }
//}


package com.redy.blogbackend.controllers;

import com.redy.blogbackend.controllers.dto.FoodItemRequest;
import com.redy.blogbackend.entities.FoodItem;
import com.redy.blogbackend.services.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping("/add")
    public FoodItem addFoodItem(@RequestBody FoodItemRequest request) throws Exception {
        LocalDate expiry = LocalDate.parse(request.getExpiryDate());
        return foodItemService.addFoodItem(3L, request.getFoodItemName(), expiry);
    }
    // Get all food items for a specific user (use constant user ID)
    @GetMapping("/list")
    public List<FoodItem> getFoodItemsForCurrentUser() throws Exception {
        Long testUserId = 3L; // Use constant user ID for testing
        return foodItemService.getFoodItemsByUser(testUserId);
    }

    // Delete a food item
    @DeleteMapping("/delete/{foodItemId}")
    public void deleteFoodItem(@PathVariable Long foodItemId) throws Exception {
        foodItemService.deleteFoodItem(foodItemId);
    }
}
