package ru.beerwaroff.controllers;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.beerwaroff.models.Hotel;
import ru.beerwaroff.models.Offer;
import ru.beerwaroff.models.Room;
import ru.beerwaroff.services.HotelService;
import ru.beerwaroff.services.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    HotelService hotelService;

    @Autowired
    RoomService roomService;

    private Model addModelForShow(Integer id, Room room, Model updatedModel) {
        Hotel hotel = hotelService.findById(id);
        room.setHotel(hotel);
        Iterable<Room> rooms = roomService.findByHotel(hotel);
        updatedModel.addAttribute("hotel", hotel);
        updatedModel.addAttribute("rooms", rooms);
        return updatedModel;
    }

    @GetMapping("/{id_hotel}")
    private String showRooms(@PathVariable("id_hotel") Integer id, @ModelAttribute("room") Room room,
                             @ModelAttribute("offer") Offer offer, Model model) {

        addModelForShow(id, room, model);

        return "rooms/show";
    }

    @GetMapping("/{id_hotel}/")
    private String showRoomsByComfort(@RequestParam("comfort") String comfort, @PathVariable("id_hotel") Integer id,
                                      @ModelAttribute("room") Room room, Model model) {

        Hotel hotel = hotelService.findById(id);
        room.setHotel(hotel);
        Iterable<Room> rooms = roomService.findByHotelAndComfort(hotel, comfort);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", rooms);
        return "rooms/show";
    }

    @PostMapping("/{id_hotel}")
    public String addRoom(@PathVariable("id_hotel") Integer id, @ModelAttribute("room") @Valid Room room,
                          BindingResult bindingResult, Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            addModelForShow(id, room, model);
            return "rooms/show";
        }

        Hotel hotel = hotelService.findById(id);
        room.setHotel(hotel);

        roomService.add(room);

        return "redirect:{id_hotel}";
    }

    @GetMapping("/update/{id}")
    public String updateRoom(@PathVariable("id") Integer id, Model model) throws Exception {
        Room room = roomService.findById(id);
        model.addAttribute("updatedRoom", room);
        model.addAttribute("hotel", room.getHotel());
        return "rooms/update";
    }
    @PatchMapping("/update/{id_room}")
    public String updateRoom(@ModelAttribute("updatedRoom") @Valid Room updatedRoom, BindingResult bindingResult,
                             @ModelAttribute("hotel") Hotel hotel, @PathVariable("id_room") Integer id, Model model) throws Exception {

        Integer id_hotel = roomService.findById(id).getHotel().getId();

        if (bindingResult.hasErrors()) {
            updatedRoom.setId(id);
            updatedRoom.setHotel(hotel);
            model.addAttribute("updatedRoom", updatedRoom);
            return "rooms/update";
        }

        roomService.update(id, updatedRoom);

        return "redirect:../../rooms/" + id_hotel;
    }

    @PostMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) throws Exception {
        Integer id_hotel = roomService.findById(id).getHotel().getId();
        roomService.deleteById(id);
        return "redirect:../../rooms/" + id_hotel;
    }
}
