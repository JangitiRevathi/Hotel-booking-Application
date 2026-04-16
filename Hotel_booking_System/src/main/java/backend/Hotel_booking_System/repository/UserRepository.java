package backend.Hotel_booking_System.repository;

import backend.Hotel_booking_System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}