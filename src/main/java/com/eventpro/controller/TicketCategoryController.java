package com.eventpro.controller;

import com.eventpro.dto.request.TicketCategoriesRequest;
import com.eventpro.dto.response.TicketCategoriesResponse;
import com.eventpro.service.TicketCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-categories")
@RequiredArgsConstructor
public class TicketCategoryController {

    private final TicketCategoryService ticketCategoryService;

    @PostMapping
    public ResponseEntity<TicketCategoriesResponse> create(
            @Valid @RequestBody TicketCategoriesRequest request) {

        TicketCategoriesResponse response =
                ticketCategoryService.creerCategorie(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketCategoriesResponse>> findAll() {

        List<TicketCategoriesResponse> categories =
                ticketCategoryService.findAll();

        return ResponseEntity.ok(categories);
    }
}