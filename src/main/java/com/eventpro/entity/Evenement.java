package com.eventpro.entity;

import com.eventpro.entity.enums.StatutEvenement;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String lieu;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private LocalDateTime dateOuvertureVente;
    private LocalDateTime dateClotureVente;

    @Enumerated(EnumType.STRING)
    private StatutEvenement statut;

    @ManyToOne
    @JoinColumn(name = "organisateur_id")
    private Organisateur organisateur;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<CategorieBillet> categories = new ArrayList<>();
    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<Promotion> promotions = new ArrayList<>();

    public void publier() {
    }

    public void annuler() {
    }

    public void verifierDisponibilite() {
    }
}
