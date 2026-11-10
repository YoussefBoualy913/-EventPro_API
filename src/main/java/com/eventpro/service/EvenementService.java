package com.eventpro.service;

import com.eventpro.dto.request.EvenementRequest;
import com.eventpro.dto.request.TicketCategoriesPerEventRequest;
import com.eventpro.dto.response.EvenementResponse;
import com.eventpro.entity.Evenement;
import com.eventpro.entity.Organisateur;
import com.eventpro.entity.TicketCategoriesPerEvent;
import com.eventpro.entity.TicketCategory;
import com.eventpro.exception.EvenementNotFoundException;
import com.eventpro.exception.OrganisateurNotFoundException;
import com.eventpro.exception.TicketCategoryNotFoundException;
import com.eventpro.mapper.EvenementMapper;
import com.eventpro.repository.EvenementRepository;
import com.eventpro.repository.OrganisateurRepository;
import com.eventpro.repository.TicketCategoryRepository;
import com.eventpro.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final EvenementMapper evenementMapper;
    private final OrganisateurRepository organisateurRepository;
    private final TicketCategoryRepository ticketCategoryRepository;

    @Transactional
    public EvenementResponse creerEvenement(EvenementRequest request) {

        Organisateur organisateur = organisateurRepository
                .findById(request.getOrganisateurId())
                .orElseThrow(() ->
                        new OrganisateurNotFoundException("Organisateur introuvable")
                );

        Evenement evenement = evenementMapper.toEntity(request);
        evenement.setOrganisateur(organisateur);

        for (TicketCategoriesPerEventRequest categorieRequest
                : request.getCategories()) {

            if (categorieRequest.getTicketCategorieId() == null) {
                throw new IllegalArgumentException(
                        "L'identifiant de la catégorie de billet est obligatoire"
                );
            }

            TicketCategory ticketCategory = ticketCategoryRepository
                    .findById(categorieRequest.getTicketCategorieId())
                    .orElseThrow(() ->
                            new TicketCategoryNotFoundException(
                                    "Catégorie introuvable"
                            )
                    );

            TicketCategoriesPerEvent categorieEvenement =
                    TicketCategoriesPerEvent.builder()
                            .ticketCategory(ticketCategory)
                            .prixUnitaire(categorieRequest.getPrixUnitaire())
                            .quantiteTotale(categorieRequest.getQuantiteTotale())
                            .quotaMaxParClient(categorieRequest.getQuotaMaxParClient())
                            .evenement(evenement)
                            .build();

            evenement.getCategories().add(categorieEvenement);
        }

        Evenement evenementEnregistre = evenementRepository.save(evenement);

        return evenementMapper.toResponse(evenementEnregistre);
    }

    public List<EvenementResponse> getAllEvenements() {
        return  evenementRepository.findAll().stream().map(evenement-> evenementMapper.toResponse(evenement)).toList();

    }

    public EvenementResponse getEvenementById(UUID id) {
        Evenement evenement = evenementRepository.findById(id).orElse(null);
         return evenementMapper.toResponse(evenement) ;
    }

    @Transactional
    public EvenementResponse updateEvenement(
            UUID id,
            EvenementRequest requestDTO
    ) {

        Evenement evenement = evenementRepository.findById(id)
                .orElseThrow(() ->
                        new EvenementNotFoundException(
                                "Événement introuvable avec l'id : " + id
                        )
                );

            evenementMapper.updateEvenementFromDTO(requestDTO, evenement);


        Evenement evenementModifie = evenementRepository.save(evenement);

        return evenementMapper.toResponse(evenementModifie);
    }

    @Transactional
    public void deleteEvenement(UUID id) {

        Evenement evenement = evenementRepository.findById(id)
                .orElseThrow(() ->
                        new EvenementNotFoundException(
                                "Événement introuvable avec l'id : " + id
                        )
                );

        evenementRepository.delete(evenement);
    }
}