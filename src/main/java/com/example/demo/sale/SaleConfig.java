package com.example.demo.sale;

import com.example.demo.client.Client;
import com.example.demo.client.ClientRepository;
import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SaleConfig {
    @Bean
    CommandLineRunner saleCommandLineRunner(SaleRepository saleRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        return args -> {
            // Fetch clients and products to be used in creating sales
            Client firstClient = clientRepository.findById(1L).orElseThrow();
            Product firstProduct = productRepository.findById(1L).orElseThrow();
            Product secondProduct = productRepository.findById(2L).orElseThrow();

            // Set of products for the sale
            Set<Product> products = new HashSet<>();
            products.add(firstProduct);
            products.add(secondProduct);

            // Creating a new sale instance
            Sale firstSale = new Sale(
                    LocalDateTime.now(),
                    200.00,
                    firstClient,
                    products
            );

            // Save this sale in the database
            saleRepository.save(firstSale);
        };
    }
}
