
package com.eventpro.controller;

import com.eventpro.service.EvenementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.eventpro.dto.request.EvenementRequest;
import com.eventpro.dto.response.EvenementResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/evenements")
@RequiredArgsConstructor
public class EventController {

    private final EvenementService evenementService;


    @PostMapping
    public ResponseEntity<EvenementResponse> createEvent(
            @Valid @RequestBody EvenementRequest request) {

        EvenementResponse response = evenementService.creerEvenement(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);    
    }


    @GetMapping
    public ResponseEntity<List<EvenementResponse>> getAllEvents() {

        List<EvenementResponse> events = evenementService.getAllEvenements();

        return ResponseEntity.ok(events);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EvenementResponse> getEventById(
            @PathVariable UUID id) {

        EvenementResponse response = evenementService.getEvenementById(id);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EvenementResponse> updateEvent(
            @PathVariable UUID id,
            @Valid @RequestBody EvenementRequest request) {

        EvenementResponse response =
                evenementService.updateEvenement(id, request);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable UUID id) {

        evenementService.deleteEvenement(id);

        return ResponseEntity.noContent().build();
    }
}
