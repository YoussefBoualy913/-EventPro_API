package com.eventpro.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCategoriesPerEventResponse {

    private UUID id;
    private String nom;
    private BigDecimal prixUnitaire;
    private Integer quantiteTotale;
    private Integer quotaMaxParClient;

    private UUID evenementId;
    private UUID ticketCategoryId;
    private String titreEvenement;
}
