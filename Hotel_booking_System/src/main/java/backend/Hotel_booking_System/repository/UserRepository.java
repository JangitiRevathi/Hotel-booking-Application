package backend.Hotel_booking_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.Hotel_booking_System.service.User;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}