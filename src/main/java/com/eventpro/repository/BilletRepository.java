package com.eventpro.repository;
import com.eventpro.entity.Billet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BilletRepository
        extends JpaRepository<Billet, UUID> {
}