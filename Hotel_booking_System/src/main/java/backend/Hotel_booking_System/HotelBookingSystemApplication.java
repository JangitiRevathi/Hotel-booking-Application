package backend.Hotel_booking_System;

import backend.Hotel_booking_System.model.Hotel;
import backend.Hotel_booking_System.model.Room;
import backend.Hotel_booking_System.repository.HotelRepository;
import backend.Hotel_booking_System.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(HotelRepository hotelRepository, RoomRepository roomRepository) {
		return args -> {
			if (hotelRepository.count() == 0) {
				Hotel hotel1 = new Hotel();
				hotel1.setName("Neon Cyber Tower");
				hotel1.setLocation("Tokyo, Japan");
				hotel1.setDescription("A premium luxury stay in the heart of Tokyo with futuristic amenities and breathtaking views.");
				hotel1.setAmenities("Free Wi-Fi, Pool, Spa, Gym");
				hotel1.setImageUrl("https://images.unsplash.com/photo-1542314831-c6a4d14d8373?ixlib=rb-4.0.3&auto=format&fit=crop&w=1200&q=80");
				hotelRepository.save(hotel1);

				Room room1 = new Room();
				room1.setRoomType("Penthouse Suite");
				room1.setPricePerNight(850.0);
				room1.setIsAvailable(true);
				room1.setHotel(hotel1);
				roomRepository.save(room1);

				Room room2 = new Room();
				room2.setRoomType("Deluxe Room");
				room2.setPricePerNight(300.0);
				room2.setIsAvailable(true);
				room2.setHotel(hotel1);
				roomRepository.save(room2);

                Hotel hotel2 = new Hotel();
				hotel2.setName("Oceanview Retreat");
				hotel2.setLocation("Malibu, California");
				hotel2.setDescription("Wake up to the sound of waves in this beautiful beachside resort.");
				hotel2.setAmenities("Beach Access, Free Breakfast, Wi-Fi");
				hotel2.setImageUrl("https://images.unsplash.com/photo-1582719508461-905c673771fd?ixlib=rb-4.0.3&auto=format&fit=crop&w=1200&q=80");
				hotelRepository.save(hotel2);

                Room room3 = new Room();
				room3.setRoomType("Oceanfront Villa");
				room3.setPricePerNight(1200.0);
				room3.setIsAvailable(true);
				room3.setHotel(hotel2);
				roomRepository.save(room3);
			}
		};
	}
}
