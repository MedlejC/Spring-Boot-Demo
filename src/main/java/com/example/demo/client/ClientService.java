package com.example.demo.client;

import jakarta.transaction.Transactional;
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

    @Transactional
    public Client addNewClient(Client client){
        logger.info("Attempting to add a new client: {}", client);
        Optional<Client> clientOptional = clientRepository.findClientByMobile(client.getMobile());
        if(clientOptional.isPresent()){
            logger.error("Attempted to add a duplicate client (by mobile): {}", client);
            throw new IllegalStateException("Client with mobile " + client.getMobile() + " already exists!");
        }
        logger.info("Successfully added client: {}", client);
        return clientRepository.save(client);
    }

    public void deleteClient(Long clientId){
        logger.info("Attempting to delete the client with ID: {}", clientId);
        boolean idExists = clientRepository.existsById(clientId);
        if(!idExists){
            logger.error("Attempted to delete a non-existing client with ID: {}", clientId);
            throw new IllegalStateException("Client with ID " + clientId + " does not exist in the database!");
        }
        clientRepository.deleteById(clientId);
        logger.info("Successfully deleted client with ID: {}", clientId);
    }

    @Transactional
    // @Transactional annotation:
    // I can use the setters from my entities to check whether I can or cannot update the selected item.
    // If I can update, I use the setters to automatically update the entities in the database.
    public void updateClient(Long clientId, String firstName, String lastName, String mobile){
        logger.info("Attempting to update client with ID: {}", clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> {
            logger.error("Attempted to update a non-existing client with ID: {}", clientId);
            return new IllegalStateException("Client with ID " + clientId + " does not exist in the database!");
        });

        if (firstName != null && !firstName.isEmpty()) {
            client.setFirstName(firstName);
            logger.info("Successfully updated client first name for ID: {}", clientId);
        }

        if (lastName != null && !lastName.isEmpty()) {
            client.setLastName(lastName);
            logger.info("Successfully updated client last name for ID: {}", clientId);
        }

        if (mobile != null && !mobile.equals(client.getMobile())) {
            Optional<Client> clientOptional = clientRepository.findClientByMobile(mobile);
            if(clientOptional.isPresent()){
                logger.error("Attempted to register mobile to an existing client: {}", firstName);
                throw new IllegalStateException("Mobile already exists!");
            }
            client.setMobile(mobile);
            logger.info("Successfully updated client mobile for ID: {}", clientId);
        }
    }
}
