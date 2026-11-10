package com.eventpro.dto.response;

import com.eventpro.entity.enums.StatutPaiement;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementResponse {

    private UUID id;
    private String reference;
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private StatutPaiement statut;

    private UUID reservationId;
    private String referenceReservation;
}
