package com.example.demo.product;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// The Product Service class has to be instantiated --> It has to be annotated as a Spring Bean
@Service
public class ProductService {
    // This class serves as the Service/Business Layer for the Product class
    // & contains all the resources needed for the Product Service/Business Layer.

    // The API Layer will talk to the Service/Business Layer to get some data.
    // This Service/Business Layer should talk to the Data Access Layer to get data (round-trip).

    public List<Product> getProducts(){
        return List.of(
                new Product(
                        1L,
                        "ToolBox",
                        "High quality plastic tool box to hold all essential items and tools.",
                        "Boxes",
                        LocalDateTime.now()
                )
        );
    }
}
