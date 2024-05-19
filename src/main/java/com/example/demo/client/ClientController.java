package com.example.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    // This class serves as the API Layer for the Client class
    // & contains all the resources needed for the Client API Layer.

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    // Reference to the Service Layer of the Client class
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    // GET method
    // GET is used to fetch an item from the database
    @GetMapping
    public List<Client> getClients(){
        logger.info("Received request to get all clients");
        return clientService.getClients();
    }

    // POST method
    // POST is used to add new items to the database
    @PostMapping
    public void registerNewClient(@RequestBody Client client){
        logger.info("Received request to add a new client: {}", client);
        clientService.addNewClient(client);
    }

    // DELETE method
    // DELETE is used to delete selected clients from the database
    @DeleteMapping("{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId){
        logger.info("Received request to delete client with ID: {}", clientId);
        clientService.deleteClient(clientId);
    }

    // PUT method
    // PUT is used to update a client in the database
    @PutMapping("{clientId}")
    public void updateClient(@PathVariable("clientId") Long clientId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String mobile
                              ){
        logger.info("Received request to update client with ID: {}", clientId);
        clientService.updateClient(clientId, firstName, lastName, mobile);
    }
}