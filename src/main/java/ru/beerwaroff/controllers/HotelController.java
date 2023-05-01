package ru.beerwaroff.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.beerwaroff.models.Address;
import ru.beerwaroff.models.Hotel;
import ru.beerwaroff.models.Image;
import ru.beerwaroff.repositories.AddressRepository;
import ru.beerwaroff.repositories.HotelRepository;
import ru.beerwaroff.repositories.ImageRepository;
import ru.beerwaroff.services.AddressService;
import ru.beerwaroff.services.HotelService;
import ru.beerwaroff.services.RoomService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Value("${image.path}")
    private String pathImage;

    @GetMapping()
    private String showHotels(@ModelAttribute("hotel") Hotel hotel, Model model) {
        Iterable<Hotel> hotels = hotelService.findAll();
        model.addAttribute("hotels", hotels);
        return "hotels/show";
    }

    @GetMapping("/")
    private String showHotelsByCity(@RequestParam("city") String city, @ModelAttribute("hotel") Hotel hotel, Model model) {
        Iterable<Hotel> hotels = hotelService.findByCity(city);
        model.addAttribute("hotels", hotels);
        return "hotels/show";
    }

    @PostMapping()
    public String addHotel(@ModelAttribute("hotel") @Valid Hotel hotel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "hotels/show";
        }

        addressService.add(hotel.getAddress());
        hotelService.add(hotel);

        return "redirect:hotels";
    }

    @GetMapping("/update/{id}")
    public String updateHotel(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("updatedHotel", hotelService.findById(id));
        return "hotels/update";
    }

    @PatchMapping("/update/{id}")
    public String updateHotel(@PathVariable("id") Integer id, @ModelAttribute("updatedHotel") @Valid Hotel updatedHotel,
                              BindingResult bindingResult, Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            updatedHotel.setId(id);
            model.addAttribute("updatedHotel", updatedHotel);
            return "hotels/update";
        }

        hotelService.update(id, updatedHotel);

        return "redirect:../../hotels";
    }

    @PostMapping("/delete/{id}")
    public String deleteHotel(@PathVariable("id") Integer id) throws Exception {
        hotelService.deleteById(id);
        return "redirect:../../hotels";
    }

}
