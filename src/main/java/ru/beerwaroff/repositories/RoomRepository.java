package ru.beerwaroff.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import ru.beerwaroff.models.Hotel;
import ru.beerwaroff.models.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    Iterable<Room> findByHotel(Hotel hotel);

    Iterable<Room> findByHotelAndComfort(Hotel hotel, String comfort);

    @Transactional
    void deleteByHotel(Hotel hotel);
}
