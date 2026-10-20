package com.eventpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorieBillet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    private Integer quantiteTotale;
    private Integer quotaMaxParClient;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;

    @OneToMany(mappedBy = "categorieBillet", cascade = CascadeType.ALL)
    private List<Billet> billets = new ArrayList<>();

    public boolean verifierStock(int quantite) {
        return false;
    }
}
