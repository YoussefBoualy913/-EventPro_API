package com.eventpro.dto.response;

import com.eventpro.entity.enums.StatutReservation;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    private UUID id;
    private String reference;
    private LocalDateTime dateCreation;
    private LocalDateTime dateExpiration;
    private StatutReservation statut;
    private BigDecimal montantTotal;

    private UUID clientId;
    private String nomClient;
    private String emailClient;

    private UUID promotionId;
    private String codePromotion;

    private List<LigneReservationResponse> lignes;
}
