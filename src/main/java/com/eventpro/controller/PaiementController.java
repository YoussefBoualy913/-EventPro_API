package com.eventpro.controller;

import com.eventpro.dto.request.PaiementRequest;
import com.eventpro.dto.response.PaiementResponse;

import com.eventpro.service.PaiementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
class PaiementController{
    private final PaiementService paiementService;

    @PostMapping
    public ResponseEntity<PaiementResponse> create(@RequestBody @Valid PaiementRequest paiementRequest){
        PaiementResponse paiementResponse = paiementService.creerPaiement(paiementRequest);
        return new ResponseEntity<>(paiementResponse, HttpStatus.CREATED);
    }

}