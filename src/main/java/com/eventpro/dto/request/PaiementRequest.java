package com.eventpro.dto.request;

import com.eventpro.entity.enums.StatutPaiement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementRequest {

    @Size(max = 50, message = "La référence ne doit pas dépasser 50 caractères")
    private String reference;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    @Digits(integer = 8, fraction = 2, message = "Le montant est invalide")
    private BigDecimal montant;

    private LocalDateTime datePaiement;

    @NotNull(message = "Le statut du paiement est obligatoire")
    private StatutPaiement statut;

    @NotNull(message = "La réservation est obligatoire")
    private UUID reservationId;
}
