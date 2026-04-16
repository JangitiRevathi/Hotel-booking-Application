package backend.Hotel_booking_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letsgo.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocationContaining(String location);
}