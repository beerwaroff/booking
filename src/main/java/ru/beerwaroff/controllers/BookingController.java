package ru.beerwaroff.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.beerwaroff.models.Offer;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @PostMapping()
    public String book(@ModelAttribute("offer") Offer offer, Model model) {
        System.out.println(offer.getCheckInDate());
        System.out.println(offer.getEvictionDate());
        model.addAttribute("offer", offer);
        return "booking";
    }

}
