package com.redy.blogbackend.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Location {
    private double lat;
    private double lon;
}
