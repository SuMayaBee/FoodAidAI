package com.redy.blogbackend.repositories;

import com.redy.blogbackend.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    // Find all food items by the user ID (foreign key)
    List<FoodItem> findByUserId(Long userId);

    // Find all food items by user ID and sort by expiry date (ascending)
    @Query("SELECT f FROM FoodItem f WHERE f.user.id = :userId ORDER BY f.expiryDate ASC")
    List<FoodItem> findAllByUserIdOrderByExpiryDateAsc(@Param("userId") Long userId);

    // Find expired food items by user ID
    @Query("SELECT f FROM FoodItem f WHERE f.user.id = :userId AND f.expiryDate < CURRENT_DATE")
    List<FoodItem> findExpiredFoodItemsByUserId(@Param("userId") Long userId);

    // Find all food items across all users (useful for admin view or reports)
    @Query("SELECT f FROM FoodItem f ORDER BY f.expiryDate ASC")
    List<FoodItem> findAllFoodItems();

    // Optional: Use a projection if you only need a subset of fields
//    @Query("SELECT f.foodItem as foodItem, f.expiryDate as expiryDate FROM FoodItem f WHERE f.user.id = :userId")
//    List<FoodItemProjection> findProjectedFoodItemsByUserId(@Param("userId") Long userId);
}
