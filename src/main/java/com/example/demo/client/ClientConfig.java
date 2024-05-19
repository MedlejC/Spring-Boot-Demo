package com.example.demo.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner clientCommandLineRunner(ClientRepository repository){
        return args -> {
            Client mazen = new Client(
                    "Mazen",
                    "Smith",
                    "76123456"
            );
            Client ali = new Client(
                    "Ali",
                    "Alawiyeh",
                    "76112112"
            );
            Client leila = new Client(
                    "Leila",
                    "Jones",
                    "76223344"
            );

            Client omar = new Client(
                    "Omar",
                    "Fakhoury",
                    "76334455"
            );

            Client sara = new Client(
                    "Sara",
                    "White",
                    "76445566"
            );

            Client hassan = new Client(
                    "Hassan",
                    "Khalil",
                    "76556677"
            );

            Client nora = new Client(
                    "Nora",
                    "Bazzi",
                    "76667788"
            );

            // Save these clients in the database
            repository.saveAll(
                    List.of(mazen, ali, leila, omar, sara, hassan, nora)
            );
        };
    }
}
