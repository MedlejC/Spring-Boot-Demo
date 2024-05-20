package com.example.demo.product;

import com.example.demo.sale.Sale;
import com.example.demo.sale.SaleRepository;
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
    private final SaleRepository saleRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    public List<Product> getProducts(){
       logger.info("Fetching all products");
        return productRepository.findAll();
    }

    @Transactional
    public Product addNewProduct(Product product) {
        logger.info("Attempting to add a new product: {}", product);
        Optional<Product> productOptional = productRepository.findProductByName(product.getName());
        if (productOptional.isPresent()) {
            logger.error("Attempted to add a duplicate product (by name): {}", product);
            throw new IllegalStateException("Product with name '" + product.getName() + "' already exists!");
        }
        logger.info("Successfully added product: {}", product);
        return productRepository.save(product);
    }


    @Transactional
    public void deleteProduct(Long productId) {
        logger.info("Attempting to delete the product with ID: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    logger.error("Attempted to delete a non-existing product with ID: {}", productId);
                    return new IllegalStateException("Product with ID " + productId + " does not exist!");
                });
        productRepository.delete(product);
    }

    @Transactional
    // @Transactional annotation:
    // I can use the setters from my entities to check whether I can or cannot update the selected item.
    // If I can update, I use the setters to automatically update the entities in the database.
    public Product updateProduct(Long productId, String name, String description, String category, Long saleId, Integer quantity, Double price) {
        logger.info("Attempting to update product with ID: {}", productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> {
            logger.error("Attempted to update a non-existing product with ID: {}", productId);
            return new IllegalStateException("Product with ID " + productId + " does not exist in the database!");
        });

        boolean isUpdated = false;  // Flag to check if any updates need to be saved

        if (name != null && !name.equals(product.getName())) {
            product.setName(name);
            isUpdated = true;
        }
        if (description != null && !description.equals(product.getDescription())) {
            product.setDescription(description);
            isUpdated = true;
        }
        if (category != null && !category.equals(product.getCategory())) {
            product.setCategory(category);
            isUpdated = true;
        }
        if (saleId != null) {
            // Fetch the associated sale and set it to the product
            Sale sale = saleRepository.findById(saleId)
                    .orElseThrow(() -> new IllegalStateException("Sale with ID " + saleId + " does not exist."));
            if (product.getSale() == null || !saleId.equals(product.getSale().getId())) {
                product.setSale(sale);
                isUpdated = true;
            }
        }
        if (quantity != null && !quantity.equals(product.getQuantity())) {
            product.setQuantity(quantity);
            isUpdated = true;
        }
        if (price != null && !price.equals(product.getPrice())) {
            product.setPrice(price);
            isUpdated = true;
        }

        if (isUpdated) {
            product = productRepository.saveAndFlush(product);  // Save and flush immediately to ensure the update is committed
            logger.info("Product with ID: {} has been updated.", productId);
        } else {
            logger.info("No updates required for the product with ID: {}", productId);
        }

        return product;
    }

}
