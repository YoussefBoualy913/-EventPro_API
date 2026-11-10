package com.eventpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

    public class LigneReservationRequest {

        @NotNull(message = "La quantité est obligatoire")
        @Positive(message = "La quantité doit être supérieure à 0")
        private Integer quantite;

        @NotNull(message = "La catégorie de billet est obligatoire")
        private UUID ticketCategoriesPerEventId;
    }
