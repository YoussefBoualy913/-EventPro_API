package com.eventpro.dto.request;

import com.eventpro.entity.enums.TypeReduction;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionRequest {

    @NotBlank(message = "Le code de promotion est obligatoire")
    @Size(max = 50, message = "Le code de promotion ne doit pas dépasser 50 caractères")
    private String code;

    @NotNull(message = "Le type de réduction est obligatoire")
    private TypeReduction typeReduction;

    @NotNull(message = "La valeur de la réduction est obligatoire")
    @DecimalMin(value = "0.00", message = "La valeur de la réduction ne peut pas être négative")
    @Digits(integer = 8, fraction = 2, message = "La valeur de la réduction est invalide")
    private BigDecimal valeurReduction;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDateTime dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDateTime dateFin;

    @PositiveOrZero(message = "La limite d'utilisation ne peut pas être négative")
    private Integer limiteUtilisation;


    private UUID evenementId;
}
