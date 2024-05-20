package com.example.demo.initialization;

import com.example.demo.client.Client;
import com.example.demo.client.ClientRepository;
import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import com.example.demo.sale.Sale;
import com.example.demo.sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class InitializationConfig {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Bean
    CommandLineRunner initializeEverything() {
        return args -> {
            initializeClients();
            initializeProducts();
            initializeSales();
        };
    }

    @Transactional
    public void initializeClients() {
        List<Client> clients = Arrays.asList(
                new Client("Mazen", "Smith", "76123456"),
                new Client("Ali", "Alawiyeh", "76112112"),
                new Client(
                        "Leila",
                        "Jones",
                        "76223344"
                ),
                new Client(
                        "Omar",
                        "Fakhoury",
                        "76334455"
                ),
                new Client(
                        "Sara",
                        "White",
                        "76445566"
                ),
                new Client(
                        "Hassan",
                        "Khalil",
                        "76556677"
                ),
                new Client(
                        "Nora",
                        "Bazzi",
                        "76667788"
                )
        );
        clientRepository.saveAll(clients);
        System.out.println("Clients initialized");
    }

    @Transactional
    public void initializeProducts() {
        List<Product> products = Arrays.asList(
                new Product("ToolBox", "High quality plastic tool box", "Boxes", LocalDateTime.now(), null, 100, 50.0),
                new Product("Hammer", "Heavy-duty steel hammer", "Tools", LocalDateTime.now(), null, 150, 15.0),
                new Product(
                        "Screwdriver",
                        "Multi-head screwdriver set with ergonomic handles.",
                        "Tools",
                        LocalDateTime.now(),
                        null, // No sale associated
                        200, // Quantity of products
                        10.0  // Price per unit
                ),
                new Product(
                        "Measuring Tape",
                        "Compact measuring tape with 25 feet of flexible ruler.",
                        "Tools",
                        LocalDateTime.now(),
                        null, // No sale associated
                        50,  // Quantity of products
                        8.0   // Price per unit
                ),
                new Product(
                        "Drill",
                        "Cordless power drill with variable speed settings.",
                        "Tools",
                        LocalDateTime.now(),
                        null, // No sale associated
                        75,  // Quantity of products
                        120.0 // Price per unit
                ),
                new Product(
                        "Wrench Set",
                        "Complete set of metric wrenches made from chrome-plated steel.",
                        "Tools",
                        LocalDateTime.now(),
                        null, // No sale associated
                        80,  // Quantity of products
                        30.0 // Price per unit
                )
        );
        productRepository.saveAll(products);
        System.out.println("Products initialized");
    }

    @Transactional
    public void initializeSales() {
        Client client = clientRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Client with ID 1 not found"));
        List<Long> productIds = Arrays.asList(1L, 2L); // Example product IDs
        List<Product> products = productRepository.findAllById(productIds);

        if (products.isEmpty()) {
            throw new IllegalStateException("Products with given IDs not found");
        }

        Sale sale = new Sale(
                LocalDateTime.now(), // creation date
                0.0, // total, to be calculated
                "John Doe", // seller's name
                client // client associated with the sale
        );

        // Set the sale for each product and update the total
        double total = 0;
        for (Product product : products) {
            product.setSale(sale); // Set the back-reference to the sale
            total += product.getPrice() * product.getQuantity(); // Assuming price is set and quantity is meaningful here
        }
        sale.setTotal(total);

        // Save the sale and automatically update products due to cascade settings
        saleRepository.save(sale);
        System.out.println("Sale initialized with total: " + total);
    }

}
