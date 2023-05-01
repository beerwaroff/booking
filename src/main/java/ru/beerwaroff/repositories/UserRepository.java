package ru.beerwaroff.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.beerwaroff.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
