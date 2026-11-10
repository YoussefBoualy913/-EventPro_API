package com.eventpro.controller;

import com.eventpro.dto.request.ReservationRequest;
import com.eventpro.dto.response.ReservationResponse;
import com.eventpro.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> create(
            @Valid @RequestBody ReservationRequest request) {

        ReservationResponse response =
                reservationService.creerReservation(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}