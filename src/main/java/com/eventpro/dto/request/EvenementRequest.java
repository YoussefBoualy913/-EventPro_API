 package com.eventpro.dto.request;

import com.eventpro.entity.enums.StatutEvenement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @NotNull(message = "La capacité est obligatoire")
    @Min(value = 1, message = "La capacité doit être supérieure à 0")
    private Integer capacity;

    private StatutEvenement statut;
     @NotNull(message = "L'organisateur est obligatoire")
    private UUID organisateurId;
     @NotEmpty(message = "L'événement doit contenir au moins une catégorie")
    private List<TicketCategoriesPerEventRequest> categories;
}
