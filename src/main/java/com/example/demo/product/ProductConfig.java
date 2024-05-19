package com.example.demo.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner productCommandLineRunner(ProductRepository repository){ // Inject ProductRepository to have access to the repo
        return args -> {
            Product toolbox = new Product(
                    "ToolBox",
                    "High quality plastic tool box to hold all essential items and tools.",
                    "Boxes",
                    LocalDateTime.now()
            );
            Product hammer = new Product(
                    "Hammer",
                    "Heavy-duty steel hammer for carpentry and construction.",
                    "Tools",
                    LocalDateTime.now()
            );

            Product screwdriver = new Product(
                    "Screwdriver",
                    "Multi-head screwdriver set with ergonomic handles.",
                    "Tools",
                    LocalDateTime.now()
            );

            Product measuringTape = new Product(
                    "Measuring Tape",
                    "Compact measuring tape with 25 feet of flexible ruler.",
                    "Tools",
                    LocalDateTime.now()
            );

            Product drill = new Product(
                    "Drill",
                    "Cordless power drill with variable speed settings.",
                    "Tools",
                    LocalDateTime.now()
            );

            Product wrenchSet = new Product(
                    "Wrench Set",
                    "Complete set of metric wrenches made from chrome-plated steel.",
                    "Tools",
                    LocalDateTime.now()
            );

            // Save these items in the database:
            repository.saveAll(
                    List.of(toolbox, hammer, screwdriver, measuringTape, drill, wrenchSet)
            );
        };
    }

}
