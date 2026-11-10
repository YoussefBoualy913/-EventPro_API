package com.eventpro.repository;

import com.eventpro.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository
        extends JpaRepository<Client, UUID> {
}