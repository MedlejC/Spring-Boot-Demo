package com.example.demo.product;

import java.time.LocalDateTime;

public class Product {
    // This Product class is the model for Products Management


    // Defining Product attributes:
    private Long id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime creation_date;

    // Constructors

    // Constructor #1: No-Arg constructor
    public Product(){
    }

    // Constructor #2: Contains all the attributes

    public Product(Long id,
                   String name,
                   String description,
                   String category,
                   LocalDateTime creation_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.creation_date = creation_date;
    }


    // Getters & Setters
}
