package backend.Hotel_booking_System.repository;
<<<<<<< HEAD
=======

>>>>>>> 15e776cc0b35aff375187a4598d9380bbe126f7b

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.Hotel_booking_System.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Long hotelId);
}