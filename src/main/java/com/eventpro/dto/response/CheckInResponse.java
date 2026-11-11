package com.eventpro.dto.response;

import com.eventpro.entity.enums.StatutTicket;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInResponse {

    private UUID billetId;
    private String code;
    private StatutTicket statut;
    private boolean valide;
    private String message;

    private UUID agentControleId;
    private LocalDateTime dateControle;
    private String nomEvenement;
}
