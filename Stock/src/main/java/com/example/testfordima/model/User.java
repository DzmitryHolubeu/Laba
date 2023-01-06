package com.example.testfordima.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    @Size(min = 1, max = 30, message = "Name should be from 1 to 30 symbols")
    private String username;

    @Column(name = "email")
    @Email(message = "Please enter your email.")
    private String email;

    @Column(name = "password")
    @Size(min = 1, max = 100, message = "Password should be from 1 to 100 symbols")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products;


}
