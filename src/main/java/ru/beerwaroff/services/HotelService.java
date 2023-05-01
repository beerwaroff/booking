package ru.beerwaroff.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beerwaroff.exceptions.NoEntityException;
import ru.beerwaroff.models.Address;
import ru.beerwaroff.models.Hotel;
import ru.beerwaroff.models.Image;
import ru.beerwaroff.repositories.AddressRepository;
import ru.beerwaroff.repositories.HotelRepository;
import ru.beerwaroff.repositories.ImageRepository;
import ru.beerwaroff.repositories.RoomRepository;

import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ImageRepository imageRepository;

    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(Integer id) {
        return hotelRepository.findById(id).orElseThrow();
    }

    public Iterable<Hotel> findByCity(String city) {
        return hotelRepository.findByAddressCity(city);
    }

    public void add(Hotel hotel) {
        Image image = imageRepository.findById(1).orElseThrow();
        hotel.setImage(image);
        hotelRepository.save(hotel);
    }

    public void update(Integer id, Hotel updatedHotel) {
        Hotel oldHotel = hotelRepository.findById(id).orElseThrow();
        Integer oldIdAddress = oldHotel.getAddress().getId();

        addressRepository.save(updatedHotel.getAddress());

        Hotel hotel = findById(id);
        hotel.setAddress(updatedHotel.getAddress());
        hotel.setNumberOfStars(updatedHotel.getNumberOfStars());
        hotel.setName(updatedHotel.getName());

        hotelRepository.save(hotel);
        addressRepository.deleteById(oldIdAddress);
    }

    public void deleteById(Integer id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        roomRepository.deleteByHotel(hotel);
        hotelRepository.deleteById(id);
    }

    public void uploadImage(Integer id, Image image) {

        Hotel hotel = findById(id);

        hotel.setImage(image);

        hotelRepository.save(hotel);

    }
}
