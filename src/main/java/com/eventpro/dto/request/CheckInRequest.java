package com.eventpro.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInRequest {

    @NotBlank(message = "Le code du billet est obligatoire")
    private String code;

    private UUID agentControleId;
}
