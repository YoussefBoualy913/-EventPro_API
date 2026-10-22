package com.eventpro.repository;
import com.eventpro.entity.CategorieBillet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategorieBilletRepository
        extends JpaRepository<CategorieBillet, UUID> {
}