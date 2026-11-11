package com.eventpro.repository;
import com.eventpro.entity.Reservation;
import com.eventpro.entity.enums.StatutReservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository
        extends JpaRepository<Reservation, UUID>{

    @Query("""
    SELECT r FROM Reservation r
    WHERE r.statut = :statut
    AND r.dateExpiration <= :maintenant
""")
    List<Reservation> findReservationsExpirees(
            @Param("statut") StatutReservation statut,
            @Param("maintenant") LocalDateTime maintenant
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Reservation r WHERE r.id = :id")
    Optional<Reservation> findByIdForUpdate(@Param("id") UUID id);
}
