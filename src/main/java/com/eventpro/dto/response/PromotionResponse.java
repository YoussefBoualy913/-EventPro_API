package com.eventpro.dto.response;

import com.eventpro.entity.enums.TypeReduction;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionResponse {

    private UUID id;
    private String code;
    private TypeReduction typeReduction;
    private BigDecimal valeurReduction;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer limiteUtilisation;
    private Integer nombreUtilisations;

    private UUID evenementId;
    private String titreEvenement;
}
