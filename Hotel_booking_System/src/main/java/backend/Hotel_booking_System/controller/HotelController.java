package backend.Hotel_booking_System.controller;

import backend.Hotel_booking_System.model.Hotel;
import backend.Hotel_booking_System.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotels(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String checkIn,
            @RequestParam(required = false) String checkOut) {
        
        LocalDate inDate = checkIn != null ? LocalDate.parse(checkIn) : null;
        LocalDate outDate = checkOut != null ? LocalDate.parse(checkOut) : null;
        
        if (location == null) location = "";
        
        return ResponseEntity.ok(hotelService.searchHotels(location, inDate, outDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
}
