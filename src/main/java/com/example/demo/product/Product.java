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

    // Constructor #3: With no ID (because the database will generate the ID for us)
    public Product(String name,
                   String description,
                   String category,
                   LocalDateTime creation_date) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.creation_date = creation_date;
    }


    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }


    // toString method:

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", creation_date=" + creation_date +
                '}';
    }
}
