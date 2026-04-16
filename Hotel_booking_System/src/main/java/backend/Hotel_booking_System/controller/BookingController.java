package backend.Hotel_booking_System.controller;


import backend.Hotel_booking_System.model.Booking;
import backend.Hotel_booking_System.service.BookingService;
import backend.Hotel_booking_System.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Booking> bookRoom(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Booking>> getHistory(@PathVariable Long userId) {
        List<Booking> history = bookingService.getBookingHistory(userId);
        return ResponseEntity.ok(history);
    }

    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        try {
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok(new MessageResponse("Booking cancelled successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
}
