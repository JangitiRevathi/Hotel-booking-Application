<<<<<<< HEAD
package com.example.letsgo.service;

import com.example.letsgo.model.Booking;
import com.example.letsgo.repository.BookingRepository;
=======
package backend.Hotel_booking_System.service;

import backend.Hotel_booking_System.model.Hotel;
import backend.Hotel_booking_System.model.Room;
import backend.Hotel_booking_System.repository.BookingRepository;
import backend.Hotel.repository.HotelRepository;
>>>>>>> 4b1f697 (Added booking)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import java.util.List;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
=======

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelService.class);

    @Autowired
    private HotelRepository hotelRepository;
>>>>>>> 4b1f697 (Added booking)

    @Autowired
    private BookingRepository bookingRepository;

<<<<<<< HEAD
    @Autowired
    private EmailService emailService;

    public Booking createBooking(Booking booking) {
        logger.info("Processing new booking for User: {}", booking.getUser().getUsername());
        
        // Final sanity check for availability could go here
        
        booking.setStatus("CONFIRMED");
        Booking savedBooking = bookingRepository.save(booking);
        logger.info("Booking successful! Booking ID: {}", savedBooking.getId());

        // Send confirmation email
        if (savedBooking.getUser().getEmail() != null) {
            emailService.sendBookingConfirmation(
                    savedBooking.getUser().getEmail(),
                    savedBooking.getUser().getUsername(),
                    savedBooking.getRoom().getHotel().getName(),
                    savedBooking.getCheckInDate().toString(),
                    savedBooking.getCheckOutDate().toString()
            );
        }

        return savedBooking;
    }

    public void cancelBooking(Long bookingId) {
        logger.info("Attempting to cancel Booking ID: {}", bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
        logger.info("Booking ID: {} has been cancelled.", bookingId);

        // Send cancellation email
        if (booking.getUser().getEmail() != null) {
            emailService.sendCancellationNotice(
                    booking.getUser().getEmail(),
                    booking.getUser().getUsername(),
                    booking.getRoom().getHotel().getName(),
                    booking.getId()
            );
        }
    }

    public List<Booking> getBookingHistory(Long userId) {
        return bookingRepository.findByUser_Id(userId);
=======
    public List<Hotel> searchHotels(String location, LocalDate checkIn, LocalDate checkOut) {
        logger.info("Searching for hotels in location: {} from {} to {}", location, checkIn, checkOut);

        List<Hotel> allInLocation = hotelRepository.findByLocationContaining(location);

        if (checkIn == null || checkOut == null) {
            return allInLocation;
        }

        // Filter hotels that have at least one room available during the specified period
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
    }

    public Hotel getHotelById(Long id) {
        logger.info("Fetching details for hotel ID: {}", id);
        return hotelRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Hotel not found with id: " + id));
>>>>>>> 4b1f697 (Added booking)
    }
}