package com.eventpro.entity;

import com.eventpro.entity.enums.StatutRemboursement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 2)
    private BigDecimal montant;

    private String motif;
    private LocalDateTime dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutRemboursement statut;

    @ManyToOne
    @JoinColumn(name = "paiement_id")
    private Paiement paiement;

    public void demander() {
    }

    public void approuver() {
    }

    public void refuser() {
    }
}
