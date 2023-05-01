package ru.beerwaroff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beerwaroff.models.FullName;
import ru.beerwaroff.repositories.FullNameRepository;

@Service
public class FullNameService {
    @Autowired
    FullNameRepository fullNameRepository;

    public void add(FullName fullName) {
        fullNameRepository.save(fullName);
    }
}
