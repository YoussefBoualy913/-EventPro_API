package com.eventpro.controller;

import com.eventpro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UserService utilisateurService;

    @GetMapping
    public ResponseEntity<?> recupererTousLesUtilisateurs() {
        return ResponseEntity.ok(
                utilisateurService.recupererTousLesUtilisateurs()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> recupererUtilisateurParId(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                utilisateurService.recupererUtilisateurParId(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(
            @PathVariable UUID id) {

        utilisateurService.supprimerUtilisateur(id);

        return ResponseEntity.noContent().build();
    }
}