package com.eventpro.dto.response;

import com.eventpro.entity.enums.StatutRemboursement;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemboursementResponse {

    private UUID id;
    private BigDecimal montant;
    private String motif;
    private LocalDateTime dateDemande;
    private StatutRemboursement statut;

    private UUID paiementId;
    private String referencePaiement;
}
