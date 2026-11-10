package com.eventpro.entity;

import com.eventpro.entity.enums.StatutBillet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String code;

    private LocalDateTime dateEmission;
    private LocalDateTime dateControle;

    @Enumerated(EnumType.STRING)
    private StatutBillet statut;

    @ManyToOne
    @JoinColumn(name = "ligne_reservation_id")
    private LigneReservation ligneReservation;



    public boolean valider() {
        return false;
    }

    public boolean controler() {
        return false;
    }
}
