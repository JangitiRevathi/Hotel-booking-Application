package backend.Hotel_booking_System.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${hotelbooking.app.emailFrom:no-reply@hotelbooking.com}")
    private String fromEmail;

    public void sendRegistrationSuccess(String email, String username) {
        String subject = "Welcome to LETSGO Luxury Hotels!";
        String content = "<h1>Welcome, " + username + "!</h1>"
                + "<p>Thank you for registering with LETSGO. Your account is now active.</p>"
                + "<p>Start exploring our premium destinations and find your perfect escape.</p>";

        sendEmail(email, subject, content);
    }

    public void sendBookingConfirmation(String email, String username, String hotelName, String checkIn, String checkOut) {
        String subject = "Booking Confirmed - " + hotelName;
        String content = "<h1>Booking Confirmed!</h1>"
                + "<p>Dear " + username + ",</p>"
                + "<p>Your stay at <strong>" + hotelName + "</strong> is confirmed.</p>"
                + "<p><strong>Check-in:</strong> " + checkIn + "</p>"
                + "<p><strong>Check-out:</strong> " + checkOut + "</p>"
                + "<p>We look forward to welcoming you!</p>";

        sendEmail(email, subject, content);
    }

    public void sendCancellationNotice(String email, String username, String hotelName, Long bookingId) {
        String subject = "Booking Cancelled - " + hotelName;
        String content = "<h1>Booking Cancelled</h1>"
                + "<p>Dear " + username + ",</p>"
                + "<p>Your booking (ID: " + bookingId + ") at <strong>" + hotelName + "</strong> has been cancelled.</p>"
                + "<p>If you did not request this, please contact our support immediately.</p>";

        sendEmail(email, subject, content);
    }

    private void sendEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            
            mailSender.send(message);
            logger.info("Email sent successfully to {}", to);
        } catch (MessagingException e) {
            logger.error("Failed to send email to " + to, e);
        }
    }
}
