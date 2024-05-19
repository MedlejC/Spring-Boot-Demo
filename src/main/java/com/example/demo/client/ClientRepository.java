package com.example.demo.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // This interface serves as the Data Access Layer for the Client class

    @Query("SELECT c FROM Client c WHERE c.mobile = ?1")
    Optional<Client> findClientByMobile(String mobile);
}
