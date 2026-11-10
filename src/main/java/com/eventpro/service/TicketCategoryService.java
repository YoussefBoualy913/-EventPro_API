package com.eventpro.service;

import com.eventpro.dto.request.TicketCategoriesRequest;
import com.eventpro.dto.response.TicketCategoriesResponse;
import com.eventpro.entity.TicketCategory;
import com.eventpro.exception.BusinessException;
import com.eventpro.mapper.TicketCategoriesMapper;
import com.eventpro.repository.TicketCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;
    private final TicketCategoriesMapper ticketCategoriesMapper ;
    @Transactional
    public TicketCategoriesResponse creerCategorie(
            TicketCategoriesRequest request) {

        if (ticketCategoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BusinessException(
                    "Cette catégorie existe déjà"
            );
        }

        TicketCategory categorie = new TicketCategory();

        categorie.setName(request.getName());

        TicketCategory categorieEnregistree =
                ticketCategoryRepository.save(categorie);

        return ticketCategoriesMapper.toResponse(categorieEnregistree);
    }

    @Transactional(readOnly = true)
    public List<TicketCategoriesResponse> findAll() {

        return ticketCategoryRepository.findAll()
                .stream()
                .map(ticketCategory-> ticketCategoriesMapper.toResponse(ticketCategory))
                .toList();
    }
}