package com.eventpro.controller;

import com.eventpro.dto.request.OrganisateurRequest;
import com.eventpro.dto.response.OrganisateurResponse;
import com.eventpro.service.OrganisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/organisateurs")
@RequiredArgsConstructor
public class OrganisateurController {

    private final OrganisateurService organisateurService;

    @PostMapping
    public ResponseEntity<OrganisateurResponse> creerOrganisateur(
            @Valid @RequestBody OrganisateurRequest request) {

        OrganisateurResponse response =
                organisateurService.creerOrganisateur(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")

    public ResponseEntity<OrganisateurResponse> recupererOrganisateurParId(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                organisateurService.recupererOrganisateurParId(id)
        );
    }
}