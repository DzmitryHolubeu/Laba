package com.example.testfordima.service;

import com.example.testfordima.model.User;
import com.example.testfordima.repository.UsersRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegistrationService {
    private final UsersRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UsersRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
    }





}
