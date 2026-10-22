 package com.eventpro.dto.request;

import com.eventpro.entity.enums.StatutEvenement;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvenementRequest {

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 150, message = "Le titre ne doit pas dépasser 150 caractères")
    private String titre;

    private String description;

    @NotBlank(message = "Le lieu est obligatoire")
    private String lieu;

    @NotNull(message = "La date de début est obligatoire")
    @Future(message = "La date de début doit être dans le futur")
    private LocalDateTime dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDateTime dateFin;

    @NotNull(message = "La date d'ouverture des ventes est obligatoire")
    private LocalDateTime dateOuvertureVente;

    @NotNull(message = "La date de clôture des ventes est obligatoire")
    private LocalDateTime dateClotureVente;

    private StatutEvenement statut;
}
