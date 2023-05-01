package ru.beerwaroff.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.beerwaroff.models.FullName;

public interface FullNameRepository extends CrudRepository<FullName, Integer> {
}
