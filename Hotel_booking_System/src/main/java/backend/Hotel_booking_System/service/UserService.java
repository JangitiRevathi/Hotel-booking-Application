package backend.Hotel_booking_System.service;

import backend.Hotel_booking_System.model.User;
import backend.Hotel_booking_System.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public User registerUser(User user) {
        logger.info("Attempting to register user: {}", user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        User savedUser = userRepository.save(user);
        logger.info("User {} successfully registered.", savedUser.getUsername());

        // Send confirmation email
        if (savedUser.getEmail() != null) {
            emailService.sendRegistrationSuccess(savedUser.getEmail(), savedUser.getUsername());
        }

        return savedUser;
    }
}