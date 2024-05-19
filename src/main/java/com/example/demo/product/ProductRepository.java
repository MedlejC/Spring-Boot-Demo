package com.example.demo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // This interface serves as the Data Access Layer for the Product class

    // This transforms to SQL such as:
    // SELECT * FROM product WHERE name = [whatever is passed]
    @Query("SELECT p FROM Product p WHERE p.name = ?1") // Product is the Java class (@Entity)
    Optional<Product> findProductByName(String name);

}
