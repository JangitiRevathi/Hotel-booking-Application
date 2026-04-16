package backend.Hotel_booking_System.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.Hotel_booking_System.model.Hotel;
import backend.Hotel_booking_System.service.HotelService;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    // Requirement: Search hotels based on location, date, and availability
    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotels(
            @RequestParam String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {

        logger.info("Received API request to search hotels in: {} from {} to {}", location, checkIn, checkOut);
        List<Hotel> hotels = hotelService.searchHotels(location, checkIn, checkOut);
        return ResponseEntity.ok(hotels);
    }

    // Requirement: View hotel details
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelDetails(@PathVariable Long id) {
        logger.info("Received API request for hotel details ID: {}", id);
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
}
