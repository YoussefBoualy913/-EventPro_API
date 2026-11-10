package com.eventpro.repository;
import com.eventpro.entity.LigneReservation;
import com.eventpro.entity.enums.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface LigneReservationRepository
        extends JpaRepository<LigneReservation, UUID> {
    @Query("""
        select coalesce(sum(l.quantite), 0)
        from LigneReservation l
        where l.reservation.client.id = :clientId
          and l.ticketCategoriesPerEvent.id = :categorieId
          and l.reservation.statut in :statuts
        """)
    int compterBilletsActifs(@Param("clientId") UUID clientId,
                             @Param("categorieId") UUID categorieId,
                             @Param("statuts") Collection<StatutReservation> statuts);

}
