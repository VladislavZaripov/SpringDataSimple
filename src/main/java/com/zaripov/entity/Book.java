package com.zaripov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @GeneratedValue (strategy = GenerationType.AUTO)
    @Id
    Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Integer year;
}