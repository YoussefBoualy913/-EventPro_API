package com.eventpro.repository;
import com.eventpro.entity.Billet;
import com.eventpro.entity.Evenement;
import com.eventpro.entity.LigneReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LigneReservationRepository
        extends JpaRepository<LigneReservation, UUID> {

}
