package com.eventpro.entity;

import com.eventpro.entity.enums.TypeReduction;
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
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private TypeReduction typeReduction;

    @Column(precision = 10, scale = 2)
    private BigDecimal valeurReduction;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer limiteUtilisation;
    private Integer nombreUtilisations;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<Reservation> reservation = new ArrayList<>();

    public boolean estValide() {
        return false;
    }

    public BigDecimal calculerReduction(BigDecimal m) {
        return BigDecimal.ZERO;
    }
}
