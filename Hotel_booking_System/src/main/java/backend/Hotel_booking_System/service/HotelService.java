package backend.Hotel_booking_System.service;

import backend.Hotel_booking_System.model.Hotel;
import backend.Hotel_booking_System.model.Room;
import backend.Hotel_booking_System.repository.BookingRepository;
import backend.Hotel_booking_System.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelService.class);

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Hotel> searchHotels(String location, LocalDate checkIn, LocalDate checkOut) {
        logger.info("Searching for hotels in location: {} from {} to {}", location, checkIn, checkOut);

        List<Hotel> allInLocation = hotelRepository.findByLocationContaining(location);

        if (checkIn == null || checkOut == null) {
            return allInLocation;
        }

        // Filter hotels that have at least one room available during the specified period
        return allInLocation.stream().filter(hotel -> {
            List<Room> rooms = hotel.getRooms();
            if (rooms == null || rooms.isEmpty()) return false;

            return rooms.stream().anyMatch(room ->
                    bookingRepository.findOverlappingBookings(room.getId(), checkIn, checkOut).isEmpty()
            );
        }).collect(Collectors.toList());
    }

    public List<Hotel> searchHotelsByLocation(String location) {
        return hotelRepository.findByLocationContaining(location);
    }

    public Hotel getHotelById(Long id) {
        logger.info("Fetching details for hotel ID: {}", id);
        return hotelRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Hotel not found with id: " + id));
    }
}