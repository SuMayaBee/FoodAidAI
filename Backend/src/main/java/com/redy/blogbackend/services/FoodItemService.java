package com.redy.blogbackend.services;

import com.redy.blogbackend.entities.FoodItem;
import com.redy.blogbackend.entities.User;
import com.redy.blogbackend.repositories.FoodItemRepository;
import com.redy.blogbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FoodItemService {
//
//    private final FoodItemRepository foodItemRepository;
//    private final UserRepository userRepository;
//
//    // Add a new food item (no need to pass userId, fetch it from context)
//    public FoodItem addFoodItem(String foodItemName, LocalDate expiryDate) throws Exception {
//        User user = getUserFromContext();
//        FoodItem foodItem = new FoodItem();
//        foodItem.setFoodItem(foodItemName);
//        foodItem.setExpiryDate(expiryDate);
//        foodItem.setDaysToExpiry(calculateDaysToExpiry(expiryDate));
//        foodItem.setExpired(expiryDate.isBefore(LocalDate.now()));
//        foodItem.setUser(user);
//
//        return foodItemRepository.save(foodItem);  // Save food item to database
//    }
//
//    // Retrieve all food items for the current logged-in user
//    public List<FoodItem> getFoodItemsForCurrentUser() throws Exception {
//        User user = getUserFromContext();
//        return foodItemRepository.findByUserId(user.getId().longValue());
//    }
//
//    // Helper method to calculate days until expiration
//    private String calculateDaysToExpiry(LocalDate expiryDate) {
//        long daysUntilExpiry = LocalDate.now().until(expiryDate).getDays();
//        return daysUntilExpiry + " days until expiration";
//    }
//
//    // Delete a food item by ID (ensure it's by the current user's food item)
//    public void deleteFoodItem(Long foodItemId) throws Exception {
//        FoodItem foodItem = foodItemRepository.findById(foodItemId)
//                .orElseThrow(() -> new Exception("Food Item not found"));
//        User user = getUserFromContext();
//        if (!foodItem.getUser().getId().equals(user.getId())) {
//            throw new Exception("You are not authorized to delete this food item.");
//        }
//        foodItemRepository.delete(foodItem);
//    }
//
//    // Get user from context (reusable method)
//    private User getUserFromContext() throws Exception {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return userRepository.findByEmail(username)
//                .orElseThrow(() -> new Exception("User not logged in!"));
//    }
//}


@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final UserRepository userRepository;

    // Add a new food item for a user (now using constant user ID)
    public FoodItem addFoodItem(Long userId, String foodItemName, LocalDate expiryDate) throws Exception {
        // Get user by the user ID (for now we use a constant user ID for testing)
        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new Exception("User not found"));

        FoodItem foodItem = new FoodItem();
        foodItem.setFoodItem(foodItemName);
        foodItem.setExpiryDate(expiryDate);
        foodItem.setDaysToExpiry(calculateDaysToExpiry(expiryDate));
        foodItem.setExpired(expiryDate.isBefore(LocalDate.now()));
        foodItem.setUser(user);

        return foodItemRepository.save(foodItem);
    }

    // Retrieve all food items for a specific user (now using constant user ID)
    public List<FoodItem> getFoodItemsByUser(Long userId) {
        return foodItemRepository.findByUserId(userId);
    }

    // Delete a food item
    public void deleteFoodItem(Long foodItemId) {
        foodItemRepository.deleteById(foodItemId);
    }

    // Helper method to calculate days until expiration
    private String calculateDaysToExpiry(LocalDate expiryDate) {
        long daysUntilExpiry = LocalDate.now().until(expiryDate).getDays();
        return daysUntilExpiry + " days until expiration";
    }
}
