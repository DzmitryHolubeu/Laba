package com.example.testfordima.service;

import com.example.testfordima.model.User;
import com.example.testfordima.repository.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UsersRepo usersRepo;

    public UserDetailsService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(usersRepo.findByUsername(username));
        if (user.isEmpty())
            throw new UsernameNotFoundException("user not found");

        return new com.example.testfordima.security.UserDetails(user.get());
    }
    public User findUserByUsername (String username){
      return   usersRepo.findByUsername(username);
    }


    public Object findAll() {
        List<User> users = usersRepo.findAll();
        return users;
    }

    @Transactional
    public void deleteById(long id) {
        log.info("Into deleteById method");
        usersRepo.deleteById(id);
    }

    @Transactional
    public void makeAdminById(long id) {
        User user = usersRepo.findById(id);
        log.info("Setting ADMIN role for person {}", user);
        user.setRole("ROLE_ADMIN");
    }

}
