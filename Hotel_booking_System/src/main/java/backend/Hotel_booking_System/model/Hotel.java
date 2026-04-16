package backend.Hotel_booking_System.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "hotels")
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;
    private String name;
    private String location;
    private String amenities;
    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "hotel")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Room> rooms;
}