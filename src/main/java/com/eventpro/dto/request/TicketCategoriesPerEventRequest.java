package com.eventpro.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCategoriesPerEventRequest {

    @NotNull(message = "Le prix unitaire est obligatoire")
    @DecimalMin(value = "0.00", message = "Le prix unitaire ne peut pas être négatif")
    @Digits(integer = 8, fraction = 2, message = "Le prix unitaire est invalide")
    private BigDecimal prixUnitaire;

    @NotNull(message = "La quantité totale est obligatoire")
    @Positive(message = "La quantité totale doit être supérieure à 0")
    private Integer quantiteTotale;

    @NotNull(message = "Le quota maximum par client est obligatoire")
    @Positive(message = "Le quota par client doit être supérieur à 0")
    private Integer quotaMaxParClient;

    private UUID evenementId;
    @NotNull(message = "La catégorie de billet est obligatoire")
    private UUID ticketCategorieId;
}
