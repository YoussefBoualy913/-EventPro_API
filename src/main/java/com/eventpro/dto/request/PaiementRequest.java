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

    @NotNull(message = "La réservation est obligatoire")
    private UUID reservationId;
}
