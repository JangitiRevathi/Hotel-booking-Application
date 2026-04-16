package backend.Hotel_booking_System.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendRegistrationSuccess(String email, String username) {
        logger.info("Registration email sent to {} for user {}", email, username);
    }

    public void sendBookingConfirmation(String email, String username, String hotelName, String checkIn, String checkOut) {
        logger.info("Booking confirmation email sent to {} for user {} at hotel {} from {} to {}",
                email, username, hotelName, checkIn, checkOut);
    }

    public void sendCancellationNotice(String email, String username, String hotelName, Long bookingId) {
        logger.info("Cancellation email sent to {} for user {} for booking {} at hotel {}",
                email, username, bookingId, hotelName);
    }
}
