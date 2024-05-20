//package com.example.demo.sale;
//
//import com.example.demo.client.Client;
//import com.example.demo.client.ClientRepository;
//import com.example.demo.product.Product;
//import com.example.demo.product.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class SaleConfig {
//
//    @Bean
//    CommandLineRunner initDatabase(SaleRepository saleRepository,
//                                   ClientRepository clientRepository,
//                                   ProductRepository productRepository) {
//        return args -> {
//            // Fetch example clients and products from the database
//            Client client = clientRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Client with ID 1 not found"));
//            List<Product> products = productRepository.findAllById(Arrays.asList(1L, 2L));
//
//            // Create a new sale
//            Sale sale = new Sale(
//                    LocalDateTime.now(), // creation date
//                    0.0, // total will be calculated
//                    "John Doe", // seller's name
//                    client, // client
//                    products // list of products
//            );
//
//            // Calculate the total based on product prices
//            double total = products.stream().mapToDouble(Product::getPrice).sum();
//            sale.setTotal(total);
//
//            // Save the sale
//            saleRepository.save(sale);
//        };
//    }
//}
