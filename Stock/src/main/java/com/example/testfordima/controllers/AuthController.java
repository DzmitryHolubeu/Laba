package com.example.testfordima.controllers;

import com.example.testfordima.model.User;
import com.example.testfordima.service.RegistrationService;
import com.example.testfordima.service.UserDetailsService;
import com.example.testfordima.valid.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;

@Slf4j
@Controller
@SessionAttributes("currentUser")
@RequestMapping("/auth")

public class AuthController implements Serializable {
    private final RegistrationService registrationService;
    private final UserValidator userValidator;
    private final UserDetailsService userDetailsService;

    public AuthController(RegistrationService registrationService, UserValidator userValidator, UserDetailsService userDetailsService) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/auth/login";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        log.info("postMapping login method");
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        model.addAttribute("currentUser", userDetails);
        log.info("Login ok {}", userDetails);
        return "redirect:/hello";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {

        log.info("Go to registration page");

        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        log.info("go to performRegistration with user {}", user);
//        userValidator.validate(user, bindingResult);
//        if(bindingResult.hasErrors()){
//            return "/registration";
//        }
        log.info("Register user {}", user);
        registrationService.register(user);
        return "redirect:/auth/login";
    }


}
