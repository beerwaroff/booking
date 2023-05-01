package ru.beerwaroff.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beerwaroff.exceptions.NoEntityException;
import ru.beerwaroff.models.Hotel;
import ru.beerwaroff.models.Image;
import ru.beerwaroff.repositories.ImageRepository;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public void add(Image image) {
        imageRepository.save(image);
    }
    public Image findById(Integer id) throws Exception {
        return imageRepository.findById(id).orElseThrow(() -> new NoEntityException(id));
    }

}
