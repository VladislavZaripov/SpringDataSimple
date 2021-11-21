package com.zaripov.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Author {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;
}