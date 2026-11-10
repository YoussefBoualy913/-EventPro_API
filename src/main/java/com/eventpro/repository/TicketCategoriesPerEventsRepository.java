package com.eventpro.repository;
import com.eventpro.entity.TicketCategoriesPerEvent;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface TicketCategoriesPerEventsRepository
        extends JpaRepository<TicketCategoriesPerEvent, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from TicketCategoriesPerEvent c where c.id in :ids order by c.id")
    List<TicketCategoriesPerEvent> findAllByIdForUpdate(@Param("ids") Collection<UUID> ids);
}