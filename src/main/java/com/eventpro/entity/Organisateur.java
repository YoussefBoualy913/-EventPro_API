package com.eventpro.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("ORGANISATEUR")
public class Organisateur extends Utilisateur {
    private String nomOrganisation;

    @OneToMany(mappedBy = "organisateur", cascade = CascadeType.ALL)
    private List<Evenement> evenements = new ArrayList<>();

    public void creerEvenement() {
    }

    public void modifierEvenement() {
    }
}
