package backend.Hotel_booking_System.repository;


import backend.Hotel_booking_System.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_Id(Long userId);

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
            "AND NOT (b.checkOutDate <= :checkIn OR b.checkInDate >= :checkOut) " +
            "AND b.status != 'CANCELLED'")
    List<Booking> findOverlappingBookings(@Param("roomId") Long roomId,
                                          @Param("checkIn") LocalDate checkIn,
                                          @Param("checkOut") LocalDate checkOut);
}
