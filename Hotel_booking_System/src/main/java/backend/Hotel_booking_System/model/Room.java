package com.example.letsgo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String roomType;
    private Double pricePerNight;
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.EAGER) // Grab the hotel details immediately
    @JoinColumn(name = "hotel_id", columnDefinition = "BIGINT")
    private Hotel hotel; 
}