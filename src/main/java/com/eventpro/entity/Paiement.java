package com.eventpro.entity;

import com.eventpro.entity.enums.StatutPaiement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String reference;

    @Column(precision = 10, scale = 2)
    private BigDecimal montant;

    private LocalDateTime datePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    @OneToOne
    @JoinColumn(name = "reservation_id", unique = true)
    private Reservation reservation;

    @OneToMany(mappedBy = "paiement", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements = new ArrayList<>();

    public boolean payer() {
        return false;
    }

    public boolean rembourser(BigDecimal m) {
        return false;
    }
}
