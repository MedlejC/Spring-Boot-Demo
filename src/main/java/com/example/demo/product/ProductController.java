package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    // This class serves as the API Layer for the Product class
    // & contains all the resources needed for the Product API Layer.

    // Reference to the service layer for Product class:
    private final ProductService productService;

    @Autowired // This annotation is used for Dependency Injection
    // What it says is that basically, the productService should be auto-wired.
    // "private final ProductService productService;" will be 'magically' instantiated for us
    // & then injected into this constructor.
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
}
