package com.redy.blogbackend.repositories;

import com.redy.blogbackend.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
