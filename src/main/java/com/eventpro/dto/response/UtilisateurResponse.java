package com.eventpro.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public class UtilisateurResponse {

    private UUID id;
    private String nom;
    private String email;
}
