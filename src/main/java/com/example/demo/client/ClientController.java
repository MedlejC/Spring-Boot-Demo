package com.example.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Client>> getClients() {
        logger.info("Received request to get all clients");
        List<Client> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    // POST method
    // POST is used to add new items to the database
    @PostMapping
    public ResponseEntity<Client> registerNewClient(@RequestBody Client client) {
        logger.info("Received request to add a new client: {}", client);
        Client createdClient = clientService.addNewClient(client);
        return ResponseEntity.ok(createdClient);
    }

    // DELETE method
    // DELETE is used to delete selected clients from the database
    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        logger.info("Received request to delete client with ID: {}", clientId);
        clientService.deleteClient(clientId);
        return ResponseEntity.ok().build();
    }

    // PUT method
    // PUT is used to update a client in the database
    @PutMapping("/{clientId}")
    public ResponseEntity<?> updateClient(@PathVariable Long clientId,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName,
                                          @RequestParam String mobile) {
        logger.info("Received request to update client with ID: {}", clientId);
        clientService.updateClient(clientId, firstName, lastName, mobile);
        return ResponseEntity.ok().build();
    }
}
