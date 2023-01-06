package com.example.testfordima.repository;

import com.example.testfordima.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepo extends JpaRepository<User, Integer> {
    User findByUsername(String name);

    List<User> findAll();

    void deleteById(long id);

    User findById(long id);
}
