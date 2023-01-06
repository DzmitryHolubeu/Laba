package com.example.testfordima.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "stocks")
public class Stock {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Size(min = 1, max = 30, message = "Name should be from 1 to 30 symbols")
    private String name;

    @Column(name = "addres")
    private String address;

    @Column(name = "count")
    private long count;

    @Column(name = "max_count")
    private long maxCount;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Product> products;

}
