package com.example.demo.sale;

import com.example.demo.product.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {
    // This class serves as the API Layer for the Sale class
    // & contains all the resources needed for the Sale API Layer.

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    // Reference to the service layer for Sale class:
    private final SaleService saleService;

    @Autowired // This annotation is used for Dependency Injection
    // What it says is that basically, the saleService should be auto-wired.
    // "private final SaleService saleService;" will be 'magically' instantiated for us
    // & then injected into this constructor.
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    // GET method
    // GET is used to fetch a sale from the database
    @GetMapping
    public ResponseEntity<List<Sale>> getSales() {
        logger.info("Received request to get all sales");
        List<Sale> sales = saleService.getSales();
        return ResponseEntity.ok(sales);
    }

    // POST method
    // POST is used to add new items to the database
    @PostMapping
    public ResponseEntity<Sale> registerNewSale(@RequestBody Sale sale){ // Take the Request Body and map it into a sale object
        logger.info("Received request to add a new sale: {}", sale);
        Sale createdSale = saleService.addNewSale(sale);
        return ResponseEntity.ok(createdSale);
    }

    // ResponseEntity is a type in Spring MVC that allows to return a full HTTP response, including status code, headers, and body.
    // Using ResponseEntity gives more control over the HTTP response sent back to the client.
    // This is particularly useful for REST APIs where there is need to indicate specific status codes or add custom headers in the response.


    // DELETE method
    // DELETE is used to delete selected sales from the database
    @DeleteMapping("{saleId}")
    public ResponseEntity<?> deleteSale(@PathVariable("saleId") Long saleId){
        logger.info("Received request to delete sale with ID: {}", saleId);
        saleService.deleteSale(saleId);
        return ResponseEntity.ok("Sale deleted successfully");
    }

    @PutMapping("{saleId}")
    public ResponseEntity<Sale> updateSale(
            @PathVariable("saleId") Long saleId,
            @RequestBody Sale updatedSale
            ){
        logger.info("Received request to update sale with ID: {}", saleId);
        Sale sale = saleService.updateSale(saleId, updatedSale);
        return ResponseEntity.ok(sale);
    }
}
