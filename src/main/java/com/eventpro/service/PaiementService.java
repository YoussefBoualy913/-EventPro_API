package com.eventpro.service;

import com.eventpro.dto.request.PaiementRequest;
import com.eventpro.dto.response.PaiementResponse;
import com.eventpro.entity.LigneReservation;
import com.eventpro.entity.Paiement;
import com.eventpro.entity.Reservation;
import com.eventpro.entity.TicketCategoriesPerEvent;
import com.eventpro.entity.enums.StatutPaiement;
import com.eventpro.entity.enums.StatutReservation;
import com.eventpro.exception.PaiementException;
import com.eventpro.exception.ReservationNotFoundException;
import com.eventpro.mapper.PaiementMapper;
import com.eventpro.repository.PaiementRepository;
import com.eventpro.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final ReservationRepository reservationRepository;
    private final TicketService ticketService;
    private final PaiementMapper paiementMapper;

    @Transactional
    public PaiementResponse creerPaiement(PaiementRequest request) {

        Reservation reservation = reservationRepository
                .findByIdForUpdate(request.getReservationId())
                .orElseThrow(() ->
                        new ReservationNotFoundException("Reservation not found"));


        if (reservation.getStatut() != StatutReservation.PENDING_PAYMENT) {
            throw new PaiementException(
                    "Cette réservation ne peut pas être payée"
            );
        }


        if (LocalDateTime.now().isAfter(reservation.getDateExpiration())) {
            throw new PaiementException(
                    "Le délai de paiement de cette réservation est expiré"
            );
        }


        if (paiementRepository.existsByReservationId(request.getReservationId())) {
            throw new PaiementException(
                    "Cette réservation possède déjà un paiement"
            );
        }



        Paiement paiement = Paiement.builder()
                .montant(reservation.getMontantTotal())
                .reference(genererReference())
                .statut(StatutPaiement.SUCCESS)
                .reservation(reservation)
                .datePaiement(LocalDateTime.now())
                .build();


        for (LigneReservation ligne : reservation.getLignes()) {

            TicketCategoriesPerEvent categorie =
                    ligne.getTicketCategoriesPerEvent();

            categorie.setPlacesReservees(
                    categorie.getPlacesReservees() - ligne.getQuantite()
            );

            categorie.setPlacesVendues(
                    categorie.getPlacesVendues() + ligne.getQuantite()
            );
        }


        reservation.setStatut(StatutReservation.CONFIRMED);

        ticketService.genererTickets(reservation);



        return paiementMapper.toResponse(paiementRepository.save(paiement));
    }

    private String genererReference() {
        return "PAY-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }
}