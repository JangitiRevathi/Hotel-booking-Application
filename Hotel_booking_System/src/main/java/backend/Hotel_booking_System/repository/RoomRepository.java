package com.example.letsgo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letsgo.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Long hotelId); // To see rooms for a specific hotel
}