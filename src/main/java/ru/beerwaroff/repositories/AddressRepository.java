package ru.beerwaroff.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.beerwaroff.models.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
