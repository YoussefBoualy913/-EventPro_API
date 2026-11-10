package com.eventpro.entity;

import com.eventpro.entity.enums.TypeReduction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private TypeReduction typeReduction;

    @Column(precision = 10, scale = 2)
    private BigDecimal valeurReduction;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer limiteUtilisation;

    @Builder.Default
    private Integer nombreUtilisations = 0;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<Reservation> reservation = new ArrayList<>();

    public boolean estValide() {

        LocalDateTime maintenant = LocalDateTime.now();


        if (maintenant.isBefore(dateDebut) || maintenant.isAfter(dateFin)) {
            return false;
        }

        if (limiteUtilisation != null &&
                nombreUtilisations >= limiteUtilisation) {
            return false;
        }

        return true;
    }

    public BigDecimal calculerReduction(BigDecimal montant) {

        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        if (typeReduction == TypeReduction.PERCENTAGE) {

            return montant
                    .multiply(valeurReduction)
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

        }

        if (typeReduction == TypeReduction.FIXED_AMOUNT) {

            return valeurReduction.min(montant);
        }

        return BigDecimal.ZERO;
    }
}
