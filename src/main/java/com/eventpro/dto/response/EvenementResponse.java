package com.eventpro.dto.response;

import com.eventpro.entity.enums.StatutEvenement;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvenementResponse {

    private UUID id;
    private String titre;
    private String description;
    private String lieu;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private LocalDateTime dateOuvertureVente;
    private LocalDateTime dateClotureVente;

    private StatutEvenement statut;

    private UUID organisateurId;
    private String nomOrganisateur;
}
