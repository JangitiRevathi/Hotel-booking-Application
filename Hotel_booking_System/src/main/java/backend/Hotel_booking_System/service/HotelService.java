<<<<<<< HEAD
package backend.Hotel_booking_System.service;

import backend.Hotel_booking_System.model.Hotel;
import backend.Hotel_booking_System.model.Room;
import backend.Hotel_booking_System.repository.BookingRepository;
import backend.Hotel_booking_System.repository.HotelRepository;
=======
package com.backend.Hotel_booking_System.service;

import com.backend.Hotel_booking_System.model.Hotel;
import com.backend.Hotel_booking_System.model.Room;
import com.backend.Hotel_booking_System.repository.BookingRepository;
import com.backend.Hotel_booking_System.repository.HotelRepository;
>>>>>>> 15e776cc0b35aff375187a4598d9380bbe126f7b
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelService.class);

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Hotel> searchHotels(String location, LocalDate checkIn, LocalDate checkOut) {
        logger.info("Searching for hotels in location: {} from {} to {}", location, checkIn, checkOut);

        List<Hotel> hotels = hotelRepository.findByLocationContaining(location);
        if (checkIn == null || checkOut == null) {
            return hotels;
        }

<<<<<<< HEAD
        return hotels.stream()
                .filter(hotel -> hotel.getRooms() != null && hotel.getRooms().stream().anyMatch(room ->
                        bookingRepository.findOverlappingBookings(room.getId(), checkIn, checkOut).isEmpty()
                ))
                .collect(Collectors.toList());
=======

        return allInLocation.stream().filter(hotel -> {
            List<Room> rooms = hotel.getRooms();
            if (rooms == null || rooms.isEmpty()) return false;

            return rooms.stream().anyMatch(room ->
                    bookingRepository.findOverlappingBookings(room.getId(), checkIn, checkOut).isEmpty()
            );
        }).collect(Collectors.toList());
    }

    public List<Hotel> searchHotelsByLocation(String location) {
        return hotelRepository.findByLocationContaining(location);
>>>>>>> 15e776cc0b35aff375187a4598d9380bbe126f7b
    }

    public Hotel getHotelById(Long id) {
        logger.info("Fetching details for hotel ID: {}", id);
<<<<<<< HEAD
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
=======
        return hotelRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Hotel not found with id: " + id));
>>>>>>> 15e776cc0b35aff375187a4598d9380bbe126f7b
    }
}
