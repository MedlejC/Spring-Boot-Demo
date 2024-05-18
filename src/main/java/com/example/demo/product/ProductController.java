package com.example.demo.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // GET method
    // GET is used to fetch an item from the database
    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    // POST method
    // POST is used to add new items to the database
    @PostMapping
    public void registerNewProduct(@RequestBody Product product){ // Take the Request Body and map it into a Product
        productService.addNewProduct(product);
    }

    // DELETE method
    // DELETE is used to delete selected items from the database
    @DeleteMapping("{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    // PUT method
    // PUT is used to update an item in the database
    @PutMapping("{productId}")
    public void updateProduct(@PathVariable("productId") Long productId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) String category
                              ){
        productService.updateProduct(productId, name, description, category);
    }



}
