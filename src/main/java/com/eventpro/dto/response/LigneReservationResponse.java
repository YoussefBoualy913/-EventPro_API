package com.eventpro.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneReservationResponse {

    private UUID id;
    private Integer quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal sousTotal;

    private UUID ticketCategoriesPerEventId;
    private String nomCategorie;
}
