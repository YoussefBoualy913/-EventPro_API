package com.eventpro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ClientRequest extends UtilisateurRequest {

    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^[0-9+().\\-\\s]{8,20}$", message = "Le numéro de téléphone est invalide")
    private String telephone;
}
