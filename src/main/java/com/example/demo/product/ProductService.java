package com.example.demo.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// The Product Service class has to be instantiated --> It has to be annotated as a Spring Bean
@Service
public class ProductService {
    // This class serves as the Service/Business Layer for the Product class
    // & contains all the resources needed for the Product Service/Business Layer.

    // The API Layer will talk to the Service/Business Layer to get some data.
    // This Service/Business Layer should talk to the Data Access Layer to get data (round-trip).

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
       return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductByName(product.getName());
        if(productOptional.isPresent()){
            throw new IllegalStateException("Product already exists!");
        }
        // Save the newly added product
        productRepository.save(product);
        System.out.println(product);
    }

    public void deleteProduct(Long productId) {
        boolean idExists = productRepository.existsById(productId);
        if(!idExists){
            throw new IllegalStateException("Product with ID " + productId + " does not exist in the database!");
        }
        // Delete the selected product
        productRepository.deleteById(productId);
    }

    @Transactional
    // @Transactional annotation:
    // I can use the setters from my entities to check whether I can or cannot update the selected item.
    // If I can update, I use the setters to automatically update the entities in the database.
    public void updateProduct(Long productId, String name, String description, String category) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException(
                "Product with ID " + productId + " does not exist in the database!")
        );
        if(name != null){
            Optional<Product> productOptional = productRepository.findProductByName(name);
            if(productOptional.isPresent()){
                throw new IllegalStateException("Product already exists!");
            }
            product.setName(name);
        }
        if(description != null)
            product.setDescription(description);
        if(category != null)
            product.setCategory(category);
    }
}
