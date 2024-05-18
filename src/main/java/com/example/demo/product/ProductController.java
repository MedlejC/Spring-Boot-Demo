package com.example.demo.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    // This class contains all the resources for the Product API Layer

    @GetMapping
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
