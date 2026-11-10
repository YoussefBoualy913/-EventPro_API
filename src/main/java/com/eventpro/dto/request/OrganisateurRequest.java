package com.eventpro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class OrganisateurRequest extends UtilisateurRequest {

    @NotBlank(message = "Le nom de l'organisation est obligatoire")
    @Size(max = 150, message = "Le nom de l'organisation ne doit pas dépasser 150 caractères")
    private String nomOrganisation;
}
