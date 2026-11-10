package com.eventpro.service;

import com.eventpro.dto.request.LigneReservationRequest;
import com.eventpro.dto.request.ReservationRequest;
import com.eventpro.dto.response.ReservationResponse;
import com.eventpro.entity.*;
import com.eventpro.entity.enums.StatutEvenement;
import com.eventpro.entity.enums.StatutReservation;
import com.eventpro.exception.*;
import com.eventpro.mapper.ReservationMapper;
import com.eventpro.repository.*;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private static final int DUREE_BLOCAGE_MINUTES = 10;

    private final EvenementRepository evenementRepository;
    private final TicketCategoriesPerEventsRepository ticketCategoriesPerEventsRepository;
    private final ClientRepository clientRepository;
    private final PromotionRepository promotionRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final LigneReservationRepository ligneReservationRepository;

    @Transactional
    public ReservationResponse creerReservation(ReservationRequest request) {

        LocalDateTime maintenant = LocalDateTime.now();


        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable"));

        Evenement evenement = evenementRepository.findById(request.getEvenementId())
                .orElseThrow(() -> new EvenementNotFoundException("Événement introuvable"));

        verifierEvenementReservable(evenement, maintenant);


        Map<UUID, Integer> quantiteParCategorie = new LinkedHashMap<>();
        for (LigneReservationRequest l : request.getLignes()) {
            if (l.getQuantite() <= 0) {
                throw new ValidationException("La quantité doit être strictement positive");
            }
            quantiteParCategorie.merge(l.getTicketCategoriesPerEventId(), l.getQuantite(), Integer::sum);
        }


        List<TicketCategoriesPerEvent> ticketCategoriesPerEvents =
                ticketCategoriesPerEventsRepository.findAllByIdForUpdate(quantiteParCategorie.keySet());

        if (ticketCategoriesPerEvents.size() != quantiteParCategorie.size()) {
            throw new TicketCategoryNotFoundException("Une ou plusieurs catégories de billets sont introuvables");
        }


        Reservation reservation = Reservation.builder()
                .reference(genererReference())
                .client(client)
                .dateCreation(maintenant)
                .dateExpiration(maintenant.plusMinutes(DUREE_BLOCAGE_MINUTES))
                .statut(StatutReservation.PENDING_PAYMENT)
                .lignes(new ArrayList<>())
                .build();

        BigDecimal sousTotal = BigDecimal.ZERO;

        for (TicketCategoriesPerEvent categorie : ticketCategoriesPerEvents) {

            if (!categorie.getEvenement().getId().equals(evenement.getId())) {
                throw new ValidationException(
                        "La catégorie " + categorie.getTicketCategory().getName() + " n'appartient pas à cet événement");
            }

            int quantite = quantiteParCategorie.get(categorie.getId());

            verifierQuota(client, categorie, quantite);


            int disponibles = categorie.getQuantiteTotale()
                    - (categorie.getPlacesReservees() + categorie.getPlacesVendues());

            if (quantite > disponibles) {
                throw new InsufficientStockException(
                        "Stock insuffisant pour " + categorie.getTicketCategory().getName()
                                + " : demandé " + quantite + ", disponible " + disponibles);
            }


            categorie.setPlacesReservees(categorie.getPlacesReservees() + quantite);


            BigDecimal prixUnitaire = categorie.getPrixUnitaire();
            BigDecimal sousTotalLigne = prixUnitaire.multiply(BigDecimal.valueOf(quantite));

            reservation.getLignes().add(LigneReservation.builder()
                    .reservation(reservation)
                    .ticketCategoriesPerEvent(categorie)
                    .quantite(quantite)
                    .prixUnitaire(prixUnitaire)
                    .sousTotal(sousTotalLigne)
                    .build());

            sousTotal = sousTotal.add(sousTotalLigne);
        }


        BigDecimal remise = BigDecimal.ZERO;
        if (request.getPromotionId() != null ) {
            Promotion promotion = promotionRepository.findById(request.getPromotionId())
                    .orElseThrow(() -> new PromotionNotFoundException("Code promo introuvable"));

            if (!promotion.estValide()) {
                throw new BusinessException("Code promo expiré ou épuisé");
            }
            remise = promotion.calculerReduction(sousTotal);
            reservation.setPromotion(promotion);
        }


        BigDecimal total = sousTotal.subtract(remise)
                .max(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
        reservation.setMontantTotal(total);


        Reservation saved = reservationRepository.save(reservation);

        return reservationMapper.toResponse(saved);
    }

    private void verifierEvenementReservable(Evenement evenement, LocalDateTime maintenant) {
        if (evenement.getStatut() != StatutEvenement.PUBLISHED) {
            throw new ValidationException("Cet événement n'est pas ouvert à la réservation");
        }
        if (evenement.getDateOuvertureVente() != null
                && maintenant.isBefore(evenement.getDateOuvertureVente())) {
            throw new ValidationException("La vente n'est pas encore ouverte");
        }
        if (evenement.getDateClotureVente() != null
                && maintenant.isAfter(evenement.getDateClotureVente())) {
            throw new ValidationException("La vente est clôturée");
        }
    }

    private void verifierQuota(Client client, TicketCategoriesPerEvent categorie, int quantiteDemandee) {
        Integer quota = categorie.getQuotaMaxParClient();
        if (quota == null) {
            return;
        }
        int dejaPris = ligneReservationRepository.compterBilletsActifs(
                client.getId(), categorie.getId(),
                List.of(StatutReservation.PENDING_PAYMENT, StatutReservation.CONFIRMED));

        if (dejaPris + quantiteDemandee > quota) {
            throw new ValidationException(
                    "Quota dépassé pour " + categorie.getTicketCategory().getName() + " (max " + quota + " par client)");
        }
    }

    private String genererReference() {
        return "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


    @Transactional
    public void annulerReservationExpiree(UUID reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));


        if (reservation.getStatut() != StatutReservation.PENDING_PAYMENT) {
            return;
        }

        for (LigneReservation ligne : reservation.getLignes()) {

            TicketCategoriesPerEvent categorie =
                    ligne.getTicketCategoriesPerEvent();

            categorie.setPlacesReservees(
                    categorie.getPlacesReservees() - ligne.getQuantite()
            );
        }

        reservation.setStatut(StatutReservation.EXPIRED);

        reservationRepository.save(reservation);
    }

}