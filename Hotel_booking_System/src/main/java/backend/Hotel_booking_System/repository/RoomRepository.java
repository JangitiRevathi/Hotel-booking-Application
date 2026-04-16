package backend.Hotel_booking_System.repository;

<<<<<<< HEAD
import backend.Hotel_booking_System.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
=======
>>>>>>> 1e41a53871b6e66513743b0263b92c27e608de37

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Long hotelId);
    List<Room> findByHotelIdAndIsAvailableTrue(Long hotelId);
}