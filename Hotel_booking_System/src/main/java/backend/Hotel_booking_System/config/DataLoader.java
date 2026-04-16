package backend.Hotel_booking_System.config;

import backend.Hotel_booking_System.model.Hotel;
import backend.Hotel_booking_System.model.Room;
import backend.Hotel_booking_System.repository.HotelRepository;
import backend.Hotel_booking_System.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void run(String... args) throws Exception {
        if (hotelRepository.count() == 0) {
            seedData();
        }
    }

    private void seedData() {
        // Sea Breeze Resort - Goa
        Hotel hotel1 = new Hotel();
        hotel1.setName("Sea Breeze Resort");
        hotel1.setLocation("Goay, India");
        hotel1.setAmenities("Pool, Beach Access, Spa, AC, WiFi");
        hotel1.setDescription("Relax by the ocean in our premium beachfront property in North Goa.");
        hotel1 = hotelRepository.save(hotel1);

        Room r1_1 = createRoom(hotel1, "Deluxe Ocean View", 5500.0);
        Room r1_2 = createRoom(hotel1, "Luxury Suite", 12500.0);
        Room r1_3 = createRoom(hotel1, "Standard Garden Room", 3500.0);
        roomRepository.saveAll(List.of(r1_1, r1_2, r1_3));

        // The Grand Palace - Udaipur
        Hotel hotel2 = new Hotel();
        hotel2.setName("The Grand Palace");
        hotel2.setLocation("Udaipur, Rajasthan");
        hotel2.setAmenities("Royal Spa, Private Lake View, Pool, AC");
        hotel2.setDescription("Experience true royalty in the city of lakes with our majestic heritage suites.");
        hotel2 = hotelRepository.save(hotel2);

        Room r2_1 = createRoom(hotel2, "Heritage King Suite", 18500.0);
        Room r2_2 = createRoom(hotel2, "Lake Facing Deluxe", 9500.0);
        roomRepository.saveAll(List.of(r2_1, r2_2));

        // Pine View Lodge - Manali
        Hotel hotel3 = new Hotel();
        hotel3.setName("Pine View Lodge");
        hotel3.setLocation("Manali, Himachal Pradesh");
        hotel3.setAmenities("Heating, Mountain View Trekking, WiFi, Restaurant");
        hotel3.setDescription("Cozy mountain retreat surrounded by apple orchards and snow-capped peaks.");
        hotel3 = hotelRepository.save(hotel3);

        Room r3_1 = createRoom(hotel3, "Mountain View Cabin", 4200.0);
        Room r3_2 = createRoom(hotel3, "Family Suite", 8000.0);
        roomRepository.saveAll(List.of(r3_1, r3_2));
        
        System.out.println("Database seeded with 3 hotels and 7 rooms.");
    }

    private Room createRoom(Hotel hotel, String type, Double price) {
        Room room = new Room();
        room.setHotel(hotel);
        room.setRoomType(type);
        room.setPricePerNight(price);
        room.setIsAvailable(true);
        return room;
    }
}
