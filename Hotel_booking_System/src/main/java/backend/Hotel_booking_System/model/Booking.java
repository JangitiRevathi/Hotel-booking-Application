package backend.Hotel_booking_System.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT")
    @JsonIgnoreProperties({"password", "role"}) // Don't send sensitive user info to React
    private User user;

    @ManyToOne(fetch = FetchType.EAGER) // Grab the room details immediately
    @JoinColumn(name = "room_id", columnDefinition = "BIGINT")
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
}