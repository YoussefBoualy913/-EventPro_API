package com.eventpro.entity;

import com.eventpro.entity.enums.StatutReservation;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private LocalDateTime dateCreation;
    private LocalDateTime dateExpiration;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<LigneReservation> lignes = new ArrayList<>();

    public BigDecimal calculerTotal() {
        return BigDecimal.ZERO;
    }

    public void confirmer() {
    }

    public void annuler() {
    }

    public void expirer() {
    }
}
