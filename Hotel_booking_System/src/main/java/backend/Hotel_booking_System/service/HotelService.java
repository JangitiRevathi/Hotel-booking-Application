package backend.Hotel_booking_System.service;

import backend.Hotel_booking_System.model.Hotel;
import backend.Hotel_booking_System.repository.HotelRepository;
<<<<<<< HEAD
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> 1e41a53871b6e66513743b0263b92c27e608de37
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

<<<<<<< HEAD
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
=======
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
>>>>>>> 1e41a53871b6e66513743b0263b92c27e608de37
    }
}