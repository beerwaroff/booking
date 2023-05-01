package ru.beerwaroff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.beerwaroff.models.Image;
import ru.beerwaroff.services.HotelService;
import ru.beerwaroff.services.ImageService;
import ru.beerwaroff.services.RoomService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Value("${image.path}")
    private String pathImage;

    @PostMapping("/hotels/{id}")
    public String uploadImageHotel(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile file)
            throws Exception {

        if (!file.isEmpty()) {

            String fileName = file.getOriginalFilename();
            Path pathAndName = Paths.get(pathImage, fileName);
            Files.write(pathAndName, file.getBytes());

            Image image = new Image("/images/" + fileName);
            imageService.add(image);
            hotelService.uploadImage(id, image);

        }

        return "redirect:../../hotels/update/{id}";
    }

    @PostMapping("/rooms/{id}")
    public String uploadImageRoom(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile file)
            throws Exception {

        if (!file.isEmpty()) {

            String fileName = file.getOriginalFilename();
            Path pathAndName = Paths.get(pathImage, fileName);
            Files.write(pathAndName, file.getBytes());

            Image image = new Image("/images/" + fileName);
            imageService.add(image);
            roomService.uploadImage(id, image);

        }

        return "redirect:../../rooms/update/{id}";
    }
}

