package ru.beerwaroff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beerwaroff.exceptions.NoEntityException;
import ru.beerwaroff.models.Hotel;
import ru.beerwaroff.models.Image;
import ru.beerwaroff.models.Room;
import ru.beerwaroff.repositories.HotelRepository;
import ru.beerwaroff.repositories.ImageRepository;
import ru.beerwaroff.repositories.RoomRepository;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ImageRepository imageRepository;

    public Iterable<Room> findByHotel(Hotel hotel) {
        return roomRepository.findByHotel(hotel);
    }

    public Iterable<Room> findByHotelAndComfort(Hotel hotel, String comfort) {
        return roomRepository.findByHotelAndComfort(hotel, comfort);
    }

    public Room findById(Integer id) throws Exception {
        return roomRepository.findById(id).orElseThrow(() -> new NoEntityException(id));
    }

    public void add(Room room) {
        Image image = imageRepository.findById(1).orElseThrow();
        room.setImage(image);
        roomRepository.save(room);
    }

    public void update(Integer id, Room updatedRoom) throws Exception {

        Room room = findById(id);

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setComfort(updatedRoom.getComfort());
        room.setNumberOfSeats(updatedRoom.getNumberOfSeats());
        room.setPrice(updatedRoom.getPrice());

        roomRepository.save(room);
    }

    public void deleteByHotelId(Integer id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        roomRepository.deleteByHotel(hotel);

    }

    public void deleteById(Integer id) {
        roomRepository.deleteById(id);
    }

    public void uploadImage(Integer id, Image image) throws Exception {

        Room room = findById(id);

        room.setImage(image);

        roomRepository.save(room);

    }
}
