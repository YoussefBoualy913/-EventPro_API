
package com.eventpro.service;

import com.eventpro.dto.request.PromotionRequest;
import com.eventpro.dto.response.PromotionResponse;
import com.eventpro.entity.Evenement;
import com.eventpro.entity.Organisateur;
import com.eventpro.entity.Promotion;

import com.eventpro.exception.PromotionNotFoundException;
import com.eventpro.exception.EvenementNotFoundException;
import com.eventpro.exception.BusinessException;
import com.eventpro.mapper.PromotionMapper;
import com.eventpro.repository.EvenementRepository;
import com.eventpro.repository.PromotionRepository;
import com.eventpro.repository.UtilisateurRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final EvenementRepository evenementRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PromotionMapper promotionMapper;

    @Transactional(readOnly = true)
    public List<PromotionResponse> recupererToutesLesPromotions() {
        return promotionRepository.findAll()
                .stream()
                .map(promotionMapper::toResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    public PromotionResponse recupererPromotionParId(UUID id) {
        Promotion promotion = trouverPromotion(id);

        return promotionMapper.toResponse(promotion);
    }


    public PromotionResponse creerPromotion(PromotionRequest request) {

        System.out.println("j'ai arive ici 1");
        Evenement evenement = evenementRepository
                .findById(request.getEvenementId())
                .orElseThrow(() ->
                        new EvenementNotFoundException(
                                "Événement introuvable"
                        )
                );
        System.out.println("j'ai arive ici 2");

        Organisateur organisateur = evenement.getOrganisateur();

        Promotion promotion = promotionMapper.toEntity(request);

        promotion.setEvenement(evenement);

        Promotion promotionEnregistree =
                promotionRepository.save(promotion);

        return promotionMapper.toResponse(promotionEnregistree);
    }


    private Promotion trouverPromotion(UUID id) {
        return promotionRepository.findById(id)
                .orElseThrow(() ->
                        new PromotionNotFoundException(
                                "Promotion introuvable : " + id
                        )
                );
    }


}
