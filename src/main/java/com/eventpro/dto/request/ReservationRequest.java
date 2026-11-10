package com.eventpro.dto.request;

import com.eventpro.entity.enums.StatutReservation;
import jakarta.validation.constraints.*;
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
public class ReservationRequest {

    private UUID clientId;

    private UUID promotionId;

    @NotNull(message = "L'événement est obligatoire")
    private UUID evenementId;

    @NotEmpty(message = "La réservation doit contenir au moins une ligne")
    private List<LigneReservationRequest> lignes;
}
