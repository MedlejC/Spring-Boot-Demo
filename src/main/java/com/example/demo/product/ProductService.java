package com.example.demo.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

// The Product Service class has to be instantiated --> It has to be annotated as a Spring Bean
@Service
public class ProductService {
    // This class serves as the Service/Business Layer for the Product class
    // & contains all the resources needed for the Product Service/Business Layer.

    // The API Layer will talk to the Service/Business Layer to get some data.
    // This Service/Business Layer should talk to the Data Access Layer to get data (round-trip).

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
       logger.info("Fetching all products");
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        logger.info("Attempting to add a new product: {}", product);
        Optional<Product> productOptional = productRepository.findProductByName(product.getName());
        if(productOptional.isPresent()){
            logger.error("Attempted to add a duplicate product (by name): {}", product);
            throw new IllegalStateException("Product already exists!");
        }
        // Save the newly added product
        productRepository.save(product);
        logger.info("Successfully added product: {}", product);
    }

    public void deleteProduct(Long productId) {
        logger.info("Attempting to delete the product with ID: {}", productId);
        boolean idExists = productRepository.existsById(productId);
        if(!idExists){
            logger.error("Attempted to delete a non-existing product with ID: {}", productId);
            throw new IllegalStateException("Product with ID " + productId + " does not exist in the database!");
        }
        // Delete the selected product
        productRepository.deleteById(productId);
        logger.info("Successfully deleted product with ID: {}", productId);
    }

    @Transactional
    // @Transactional annotation:
    // I can use the setters from my entities to check whether I can or cannot update the selected item.
    // If I can update, I use the setters to automatically update the entities in the database.
    public void updateProduct(Long productId, String name, String description, String category) {
        logger.info("Attempting to update product with ID: {}", productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> {
             logger.error("Attempted to update a non-existing product with ID: {}", productId);
             return new IllegalStateException("Product with ID " + productId + " does not exist in the database!");
        });
        if(name != null){
            Optional<Product> productOptional = productRepository.findProductByName(name);
            if(productOptional.isPresent()){
                logger.error("Attempted to rename product to an existing name: {}", name);
                throw new IllegalStateException("Product already exists!");
            }
            product.setName(name);
            logger.info("Successfully updated product name to: {}", name);
        }
        if(description != null) {
            product.setDescription(description);
            logger.info("Successfully updated product description for ID: {}", productId);
        }
        if(category != null) {
            product.setCategory(category);
            logger.info("Successfully updated product category for ID: {}", productId);
        }
    }
}
