package com.eventpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCategoriesPerEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    private Integer quantiteTotale;
    private Integer quotaMaxParClient;
    @Builder.Default
    private Integer placesReservees = 0;

    @Builder.Default
    private Integer placesVendues = 0;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;

    @ManyToOne
    @JoinColumn(name = "ticketCategory_id")
    private TicketCategory ticketCategory;

    @OneToMany(mappedBy = "ticketCategoriesPerEvent", cascade = CascadeType.ALL)
    private List<LigneReservation> ligneReservations = new ArrayList<>();

    public boolean verifierStock(int quantite) {
        return false;
    }
}
