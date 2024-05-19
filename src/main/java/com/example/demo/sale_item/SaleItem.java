package com.example.demo.sale_item;

import com.example.demo.product.Product;
import com.example.demo.sale.Sale;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    @JsonBackReference
    private Sale sale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;
    private double price;


    // Constructors

    // Constructor #1: No-Arg constructor
    public SaleItem() {
    }

    // Constructor #2: Contains all the attributes
    public SaleItem(Long id, Sale sale, Product product, int quantity, double price) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Constructor #3: With no ID (because the database will generate the ID for us)
    public SaleItem(Sale sale, Product product, int quantity, double price) {
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        return "SaleItem{" +
                "id=" + id +
                ", sale=" + sale +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
