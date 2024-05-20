//package com.example.demo.product;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Configuration
//public class ProductConfig {
//
//    @Bean
//    CommandLineRunner productCommandLineRunner(ProductRepository repository) {
//        return args -> {
//            Product toolbox = new Product(
//                    "ToolBox",
//                    "High quality plastic tool box to hold all essential items and tools.",
//                    "Boxes",
//                    LocalDateTime.now(),
//                    null, // No sale associated during initialization
//                    100, // Quantity of products
//                    50.0  // Price per unit
//            );
//            Product hammer = new Product(
//                    "Hammer",
//                    "Heavy-duty steel hammer for carpentry and construction.",
//                    "Tools",
//                    LocalDateTime.now(),
//                    null, // No sale associated
//                    150, // Quantity of products
//                    15.0  // Price per unit
//            );
//            Product screwdriver = new Product(
//                    "Screwdriver",
//                    "Multi-head screwdriver set with ergonomic handles.",
//                    "Tools",
//                    LocalDateTime.now(),
//                    null, // No sale associated
//                    200, // Quantity of products
//                    10.0  // Price per unit
//            );
//            Product measuringTape = new Product(
//                    "Measuring Tape",
//                    "Compact measuring tape with 25 feet of flexible ruler.",
//                    "Tools",
//                    LocalDateTime.now(),
//                    null, // No sale associated
//                    50,  // Quantity of products
//                    8.0   // Price per unit
//            );
//            Product drill = new Product(
//                    "Drill",
//                    "Cordless power drill with variable speed settings.",
//                    "Tools",
//                    LocalDateTime.now(),
//                    null, // No sale associated
//                    75,  // Quantity of products
//                    120.0 // Price per unit
//            );
//            Product wrenchSet = new Product(
//                    "Wrench Set",
//                    "Complete set of metric wrenches made from chrome-plated steel.",
//                    "Tools",
//                    LocalDateTime.now(),
//                    null, // No sale associated
//                    80,  // Quantity of products
//                    30.0 // Price per unit
//            );
//
//            // Save these items in the database:
//            repository.saveAll(
//                    List.of(toolbox, hammer, screwdriver, measuringTape, drill, wrenchSet)
//            );
//        };
//    }
//}
