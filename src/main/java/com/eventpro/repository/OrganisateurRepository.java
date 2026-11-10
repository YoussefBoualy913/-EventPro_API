package com.eventpro.repository;

import com.eventpro.entity.Organisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganisateurRepository
        extends JpaRepository<Organisateur, UUID> {
}