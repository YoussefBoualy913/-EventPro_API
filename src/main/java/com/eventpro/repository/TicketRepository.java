package com.eventpro.repository;
import com.eventpro.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository
        extends JpaRepository<Ticket, UUID> {
    boolean existsByCode(String code);
}