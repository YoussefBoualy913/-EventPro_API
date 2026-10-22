package com.eventpro.repository;
import com.eventpro.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromotionRepository
        extends JpaRepository<Paiement,UUID> {

}

