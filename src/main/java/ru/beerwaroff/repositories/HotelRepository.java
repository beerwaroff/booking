package ru.beerwaroff.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.beerwaroff.models.Address;
import ru.beerwaroff.models.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
    Iterable<Hotel> findByAddressCity(String city);
}
