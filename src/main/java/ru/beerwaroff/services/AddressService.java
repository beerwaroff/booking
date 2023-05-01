package ru.beerwaroff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beerwaroff.models.Address;
import ru.beerwaroff.repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public void add(Address address) {
        addressRepository.save(address);
    }
}
