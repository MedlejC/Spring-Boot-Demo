package com.example.demo.sale;

import com.example.demo.client.Client;
import com.example.demo.product.Product;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "sale_products",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;


    // Constructors

    // Constructor #1: No-Arg constructor
    public Sale(){

    }

    // Constructor #2: Contains all the attributes
    public Sale(Long id, LocalDateTime creationDate, Double total, Client client, Set<Product> products) {
        this.id = id;
        this.creationDate = creationDate;
        this.total = total;
        this.client = client;
        this.products = products;
    }

    // Constructor #3: With no ID (because the database will generate the ID for us)
    public Sale(LocalDateTime creationDate, Double total, Client client, Set<Product> products) {
        this.creationDate = creationDate;
        this.total = total;
        this.client = client;
        this.products = products;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    // toString method:

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", total=" + total +
                ", client=" + client +
                ", products=" + products +
                '}';
    }
}
