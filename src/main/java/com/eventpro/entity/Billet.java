package com.eventpro.entity;

import com.eventpro.entity.enums.StatutBillet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private LocalDateTime dateEmission;
    private LocalDateTime dateControle;

    @Enumerated(EnumType.STRING)
    private StatutBillet statut;

    @ManyToOne
    @JoinColumn(name = "ligne_reservation_id")
    private LigneReservation ligneReservation;

    @ManyToOne
    @JoinColumn(name = "categorie_billet_id")
    private CategorieBillet categorieBillet;

    public boolean valider() {
        return false;
    }

    public boolean controler() {
        return false;
    }
}
