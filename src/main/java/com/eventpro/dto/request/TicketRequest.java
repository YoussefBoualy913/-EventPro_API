package com.eventpro.dto.request;

import com.eventpro.entity.enums.StatutBillet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequest {

    @Size(max = 50, message = "Le code ne doit pas dépasser 50 caractères")
    private String code;

    @NotNull(message = "La date d'émission est obligatoire")
    private LocalDateTime dateEmission;

    private LocalDateTime dateControle;

    @NotNull(message = "Le statut du billet est obligatoire")
    private StatutBillet statut;

    private UUID ligneReservationId;

    private UUID categorieBilletId;
}
