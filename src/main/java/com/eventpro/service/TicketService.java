package com.eventpro.service;

import com.eventpro.entity.LigneReservation;
import com.eventpro.entity.Reservation;
import com.eventpro.entity.Ticket;
import com.eventpro.entity.enums.StatutTicket;
import com.eventpro.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;

    @Transactional
    public List<Ticket> genererTickets(Reservation reservation) {

        List<Ticket> tickets = new ArrayList<>();

        for (LigneReservation ligne : reservation.getLignes()) {

            for (int i = 0; i < ligne.getQuantite(); i++) {

                Ticket ticket = Ticket.builder()
                        .code("TCK-" + UUID.randomUUID()
                                .toString().substring(0, 8).toUpperCase())
                        .dateEmission(LocalDateTime.now())
                        .statut(StatutTicket.VALID)
                        .ligneReservation(ligne)
                        .build();

                tickets.add(ticket);
            }
        }

        return ticketRepository.saveAll(tickets);
    }
}
