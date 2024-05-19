package com.example.demo.sale;

import com.example.demo.sale_item.SaleItemRepository;
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
    private final SaleItemRepository saleItemRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleItemRepository saleItemRepository){
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
    }

    public List<Sale> getSales(){
        logger.info("Fetching all sales");
        return saleRepository.findAll();
    }

    @Transactional
    public Sale addNewSale(Sale sale){
        logger.info("Adding new sale: {}", sale);
        sale.getSaleItems().forEach(item -> {
            item.setSale(sale);
            saleItemRepository.save(item);
        });
        return saleRepository.save(sale);
    }


    public void deleteSale(Long saleId) {
        logger.info("Deleting sale with ID: {}", saleId);
        if(!saleRepository.existsById(saleId)){
            logger.error("Sale with ID {} not found", saleId);
            throw new IllegalStateException("Sale with ID " + saleId + " does not exist.");
        }
        saleRepository.deleteById(saleId);
    }

    @Transactional
    public Sale updateSale(Long saleId, Sale updatedSale) {
        logger.info("Updating sale with ID: {}", saleId);
        return saleRepository.findById(saleId).map(sale -> {
            sale.setCreationDate(updatedSale.getCreationDate());
            sale.setTotal(updatedSale.getTotal());
            sale.setClient(updatedSale.getClient());
            sale.setSeller(updatedSale.getSeller());

            // Clear existing items and add updated ones:
            saleItemRepository.deleteAll(sale.getSaleItems()); // Removes old items
            updatedSale.getSaleItems().forEach(item -> {
                item.setSale(sale); // Sets the sale for each new item
                saleItemRepository.save(item);
            });
            sale.setSaleItems(updatedSale.getSaleItems());
            return saleRepository.save(sale);
        }).orElseThrow(() -> new IllegalStateException("Sale with ID " + saleId + " does not exist."));
    }
}
