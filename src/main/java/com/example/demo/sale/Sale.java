package com.example.demo.sale;

import com.example.demo.client.Client;
import com.example.demo.product.Product;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private Double total;
    private String seller;

    @ManyToOne // The @ManyToOne association between Sale and Client indicates that each sale is linked to one client
    @JoinColumn(name = "client_id", nullable = false) // Used to specify which column in the Sale table should be used as a foreign key to reference the Client
    private Client client;


//    // This annotation specifies that each Sale can have multiple Product entries.
//    // The mappedBy attribute points to the sale field in the Product class, which is the back-reference to the Sale.
//    // -----
//    // cascade = CascadeType.ALL: Ensures that operations like save, update, and delete on Sale are cascaded to SaleItem.
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;


    // Constructors

    // Constructor #1: No-Arg constructor
    public Sale(){

    }

    // Constructor #2: Contains all the attributes
    public Sale(Long id, LocalDateTime creationDate, Double total, String seller, Client client, List<Product> products) {
        this.id = id;
        this.creationDate = creationDate;
        this.total = total;
        this.seller = seller;
        this.client = client;
        this.products = products;

    }

    // Constructor #3: With no ID (because the database will generate the ID for us)
    public Sale(LocalDateTime creationDate, Double total, String seller, Client client, List<Product> products) {
        this.creationDate = creationDate;
        this.total = total;
        this.seller = seller;
        this.client = client;
        this.products = products;

    }

    // Constructor #4: With no ID & products (because the database will generate and assign them for us)
    public Sale(LocalDateTime creationDate, Double total, String seller, Client client) {
        this.creationDate = creationDate;
        this.total = total;
        this.seller = seller;
        this.client = client;
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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

//    public List<SaleItem> getSaleItems() {
//        return saleItems;
//    }
//
//    public void setSaleItems(List<SaleItem> saleItems) {
//        this.saleItems = saleItems;
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


// toString method:


    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", total=" + total +
                ", seller='" + seller + '\'' +
                ", client=" + client +
                ", products=" + products +
                '}';
    }
}
