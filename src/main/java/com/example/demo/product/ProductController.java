package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    // This class serves as the API Layer for the Product class
    // & contains all the resources needed for the Product API Layer.

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    // Reference to the service layer for Product class:
    private final ProductService productService;

    @Autowired // This annotation is used for Dependency Injection
    // What it says is that basically, the productService should be auto-wired.
    // "private final ProductService productService;" will be 'magically' instantiated for us
    // & then injected into this constructor.
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET method
    // GET is used to fetch an item from the database
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        logger.info("Received request to get all products");
        return ResponseEntity.ok(productService.getProducts()); // Returns a list of products with a 200 OK status
    }

    // POST method
    // POST is used to add new items to the database
    @PostMapping
    public ResponseEntity<Product> registerNewProduct(@RequestBody Product product) { // Take the Request Body and map it into a Product
        logger.info("Received request to add a new product: {}", product);
        Product createdProduct = productService.addNewProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);  // Return a 201 Created status to indicate successful creation
    }

    // DELETE method
    // DELETE is used to delete selected items from the database
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        logger.info("Received request to delete product with ID: {}", productId);
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();  // Return a 200 OK status to indicate successful deletion
    }
    // PUT method
    // PUT is used to update an item in the database
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String description,
                                                 @RequestParam(required = false) String category,
                                                 @RequestParam(required = false) Long saleId,
                                                 @RequestParam(required = false) Integer quantity,
                                                 @RequestParam(required = false) Double price) {
        logger.info("Received request to update product with ID: {}", productId);
        Product updatedProduct = productService.updateProduct(productId, name, description, category, saleId, quantity, price);
        return ResponseEntity.ok(updatedProduct);  // Return the updated product with a 200 OK status
    }



}
