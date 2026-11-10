package com.eventpro.dto.request;

import com.eventpro.entity.enums.StatutRemboursement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
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
public class RemboursementRequest {

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    @Digits(integer = 8, fraction = 2, message = "Le montant est invalide")
    private BigDecimal montant;

    @NotBlank(message = "Le motif est obligatoire")
    @Size(max = 255, message = "Le motif ne doit pas dépasser 255 caractères")
    private String motif;

    private LocalDateTime dateDemande;

    @NotNull(message = "Le statut du remboursement est obligatoire")
    private StatutRemboursement statut;

    @NotNull(message = "Le paiement est obligatoire")
    private UUID paiementId;
}
