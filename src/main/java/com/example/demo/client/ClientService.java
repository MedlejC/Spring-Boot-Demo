package com.example.demo.client;


import com.example.demo.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// The Client Service class has to be instantiated --> It has to be annotated as a Spring Bean
@Service // for semantics, instead of Bean
public class ClientService {
    // This class serves as the Service/Business Layer for the Client class
    // & contains all the resources needed for the Client Service/Business Layer.

    // The API Layer will talk to the Service/Business Layer to get some data.
    // This Service/Business Layer should talk to the Data Access Layer to get data (round-trip).

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients(){
        logger.info("Fetching all clients");
        return clientRepository.findAll();
    }

    public void addNewClient(Client client){
        logger.info("Attempting to add a new client: {}", client);
        clientRepository.save(client);
        logger.info("Successfully added client: {}", client);
    }
}
