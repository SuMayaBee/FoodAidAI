package com.redy.blogbackend.repositories;

import com.redy.blogbackend.entities.User;
import com.redy.blogbackend.entities.projections.ListUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    // Custom query to fetch users along with their food items in a single query
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.foodItems WHERE u.id = :userId")
    Optional<User> findUserWithFoodItems(Integer userId);

    @Query("SELECT u from User u ORDER BY u.id DESC")
    List<ListUser> listUsers();
}
