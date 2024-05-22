package com.example.demo.sale;

import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    // This class serves as the Service/Business Layer for the Sale class
    // & contains all the resources needed for the Sale Service/Business Layer.

    // The API Layer will talk to the Service/Business Layer to get some data.
    // This Service/Business Layer should talk to the Data Access Layer to get data (round-trip).

    private static final Logger logger = LoggerFactory.getLogger(SaleService.class);
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, ProductRepository productRepository){
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    public List<Sale> getSales(){
        logger.info("Fetching all sales");
        return saleRepository.findAll();
    }

    @Transactional
    public Sale addNewSale(Sale sale){
        logger.info("Adding new sale: {}", sale);
        // Associate and save each product with the new sale
        sale.getProducts().forEach(product -> {
            product.setSale(sale); // Set the sale for each product
            productRepository.save(product); // Save each product to update the sale association
        });
        return saleRepository.save(sale);
    }


    public void deleteSale(Long saleId) {
        logger.info("Deleting sale with ID: {}", saleId);
        Sale sale = saleRepository.findById(saleId).orElseThrow(() -> new IllegalStateException("Sale with ID " + saleId + " does not exist."));
        sale.getProducts().forEach(product -> {
            product.setSale(null);
            productRepository.save(product);
        });
        saleRepository.delete(sale);
    }

    @Transactional
    public Sale updateSale(Long saleId, Sale updatedSale) {
        logger.info("Updating sale with ID: {}", saleId);
        Sale sale = saleRepository.findById(saleId).orElseThrow(() -> new IllegalStateException("Sale with ID " + saleId + " does not exist."));
        // Unlink old products if necessary
        sale.getProducts().forEach(product -> {
            product.setSale(null);
            productRepository.save(product);
        });

        // Link new products
        updatedSale.getProducts().forEach(product -> {
            product.setSale(sale);
            productRepository.save(product);
        });

        sale.setProducts(updatedSale.getProducts());
        sale.setTotal(updatedSale.getTotal());
        sale.setCreationDate(updatedSale.getCreationDate());
        sale.setClient(updatedSale.getClient());
        sale.setSeller(updatedSale.getSeller());

        return saleRepository.save(sale);

    }
}
