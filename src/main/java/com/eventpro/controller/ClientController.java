package com.eventpro.controller;

import com.eventpro.dto.request.ClientRequest;
import com.eventpro.dto.request.OrganisateurRequest;
import com.eventpro.dto.response.ClientResponse;
import com.eventpro.dto.response.OrganisateurResponse;
import com.eventpro.service.ClientService;
import com.eventpro.service.OrganisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor

public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> creerClient(
            @Valid @RequestBody ClientRequest request) {

        ClientResponse response =
                clientService.creerClient(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")

    public ResponseEntity<ClientResponse> recupererClientParId(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                clientService.recupererClient(id)
        );
    }



}