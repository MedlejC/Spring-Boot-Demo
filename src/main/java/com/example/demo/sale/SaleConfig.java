package com.example.demo.sale;

import com.example.demo.client.Client;
import com.example.demo.client.ClientRepository;
import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import com.example.demo.sale_item.SaleItem;
import com.example.demo.sale_item.SaleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class SaleConfig {

    @Bean
    CommandLineRunner initDatabase(SaleRepository saleRepository,
                                   ClientRepository clientRepository,
                                   ProductRepository productRepository,
                                   SaleItemRepository saleItemRepository) {
        return args -> {
            // Fetch example clients and products from the database
            Client client = clientRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Client with ID 1 not found"));
            Product product1 = productRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Product with ID 1 not found"));
            Product product2 = productRepository.findById(2L).orElseThrow(() -> new IllegalArgumentException("Product with ID 2 not found"));

            // Create a new sale
            Sale sale = new Sale(
                    LocalDateTime.now(), // creation date
                    0.0, // total will be calculated
                    "John Doe", // seller's name
                    client, // client
                    null // initially no direct products
            );

            // Create and save sale items
            SaleItem saleItem1 = new SaleItem(sale, product1, 5, 19.99); // 5 units of product1 at $19.99 each
            SaleItem saleItem2 = new SaleItem(sale, product2, 2, 29.99); // 2 units of product2 at $29.99 each

            // Set sale items and calculate total
            sale.setSaleItems(Arrays.asList(saleItem1, saleItem2));
            sale.setTotal(sale.getSaleItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum());

            // Save the sale
            saleRepository.save(sale);
        };
    }
}