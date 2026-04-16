package backend.Hotel_booking_System.service;

import backend.Hotel_booking_System.model.Booking;
import backend.Hotel_booking_System.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmailService emailService;

    public Booking createBooking(Booking booking) {
        logger.info("Processing new booking for User: {}", booking.getUser().getUsername());

        booking.setStatus("CONFIRMED");
        Booking savedBooking = bookingRepository.save(booking);
        logger.info("Booking successful! Booking ID: {}", savedBooking.getId());

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
    }
}

