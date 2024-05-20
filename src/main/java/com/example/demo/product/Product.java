package com.example.demo.product;

import com.example.demo.sale.Sale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity // This maps the Product class to the database medlejstore
@Table // Used for the table in the database medlejstore
public class Product {
    // This Product class is the model for Products Management


    // Defining Product attributes:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime creation_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id") // foreign key in Product table pointing to Sale
    @JsonIgnore
    private Sale sale;

    @Transient  // This field is not persisted, it's just used for serialization
    @JsonProperty("saleId")  // This annotation specifies the JSON field name
    public Long getSaleId() {
        return (this.sale != null ? this.sale.getId() : null);
    }

    private int quantity;
    private double price;

    // Constructors

    // Constructor #1: No-Arg constructor
    public Product(){
    }

    // Constructor #2: Contains all the attributes
    public Product(Long id,
                   String name,
                   String description,
                   String category,
                   LocalDateTime creation_date,
                   Sale sale,
                   int quantity,
                   double price
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.creation_date = creation_date;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
    }

    // Constructor #3: With no ID (because the database will generate the ID for us)
    public Product(String name,
                   String description,
                   String category,
                   LocalDateTime creation_date,
                   Sale sale,
                   int quantity,
                   double price
                   ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.creation_date = creation_date;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
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

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
                ", sale=" + sale +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
