package com.redy.blogbackend.controllers;

import com.redy.blogbackend.entities.Donation;
import com.redy.blogbackend.services.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    // Add a new donation
    @PostMapping("/add")
    public ResponseEntity<Donation> addDonation(@RequestBody Donation donation) {
        Donation savedDonation = donationService.saveDonation(donation);
        return ResponseEntity.ok(savedDonation); // The saved donation will include the generated id
    }


    // Get all donations
    @GetMapping("/list")
    public ResponseEntity<List<Donation>> getAllDonations() {
        List<Donation> donations = donationService.getAllDonations();
        return ResponseEntity.ok(donations);
    }
}
