package com.example.demo.sale;

import com.example.demo.client.Client;
import com.example.demo.product.Product;
import com.example.demo.sale_item.SaleItem;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

//    @ManyToMany // many-to-many relationship between Sale and Product
//    @JoinTable( // specifies the table that links Sale and Product entities together, along with the join columns (sale_id and product_id)
//            name = "sale_products",
//            joinColumns = @JoinColumn(name = "sale_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private Set<Product> products;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    // This annotation specifies that each Sale can have multiple SaleItem entries.
    // The mappedBy attribute points to the sale field in the SaleItem class, which is the back-reference to the Sale.
    // -----
    // cascade = CascadeType.ALL, orphanRemoval = true: Ensures that operations like save, update, and delete on Sale are cascaded to SaleItem.
    // Orphan removal is enabled so that when a SaleItem is removed from the collection, it’s automatically deleted from the database.
    private List<SaleItem> saleItems;


    // Constructors

    // Constructor #1: No-Arg constructor
    public Sale(){

    }

    // Constructor #2: Contains all the attributes
    public Sale(Long id, LocalDateTime creationDate, Double total, String seller, Client client, List<SaleItem> saleItems) {
        this.id = id;
        this.creationDate = creationDate;
        this.total = total;
        this.seller = seller;
        this.client = client;
        this.saleItems = saleItems;
    }

    // Constructor #3: With no ID (because the database will generate the ID for us)
    public Sale(LocalDateTime creationDate, Double total, String seller, Client client, List<SaleItem> saleItems) {
        this.creationDate = creationDate;
        this.total = total;
        this.seller = seller;
        this.client = client;
        this.saleItems = saleItems;
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

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
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
                ", saleItems=" + saleItems +
                '}';
    }
}
