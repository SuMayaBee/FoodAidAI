package com.redy.blogbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "food_items")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Primary key for food_items table
    private Long id;

    private String foodItem;  // Name of the food item
    private LocalDate expiryDate;  // Expiry date of the food item
    private String daysToExpiry;  // Days until expiry
    private boolean expired;  // Indicates if the food item is expired

    // Many food items belong to one user
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Reference to the User entity

    @CreatedDate
    @Column(updatable = false)
    private long createdAt;

    @LastModifiedDate
    private long updatedAt;
}
