package ru.beerwaroff.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beerwaroff.models.Role;
import ru.beerwaroff.models.User;
import ru.beerwaroff.repositories.UserRepository;
import ru.beerwaroff.services.FullNameService;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FullNameService fullNameService;

    @GetMapping()
    public String registration(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (user.getFullName().getFirstname().length() < 1 || user.getFullName().getSurname().length() < 1) {
            model.addAttribute("name_error", "Имя и фамилия должны быть заполнены");
            return "registration";
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwords_do_not_match", "Пароли не совпадают");
            return "registration";
        }

        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());

        if (userFromDb.isPresent()) {
            model.addAttribute("user_exists", "Пользователь с такой почтой уже существует");
            return "registration";
        }

        user.setRoles(Collections.singleton(Role.ROLE_USER));

        fullNameService.add(user.getFullName());
        userRepository.save(user);

        return "redirect:/login";
    }
}
