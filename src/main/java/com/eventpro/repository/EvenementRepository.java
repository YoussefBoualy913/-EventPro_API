package com.eventpro.repository;
import com.eventpro.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EvenementRepository
        extends JpaRepository<Evenement, UUID> {

}

