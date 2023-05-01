package ru.beerwaroff.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.beerwaroff.models.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {

}
