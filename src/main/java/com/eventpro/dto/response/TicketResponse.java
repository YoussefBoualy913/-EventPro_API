package com.eventpro.dto.response;

import com.eventpro.entity.enums.StatutTicket;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {

    private UUID id;
    private String code;
    private LocalDateTime dateEmission;
    private LocalDateTime dateControle;
    private StatutTicket statut;

    private UUID ligneReservationId;
    private UUID categorieBilletId;
    private String nomCategorie;
    private BigDecimal prixUnitaire;
}
