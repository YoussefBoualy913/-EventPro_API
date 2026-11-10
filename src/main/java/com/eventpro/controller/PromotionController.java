
package com.eventpro.controller;

import com.eventpro.dto.request.PromotionRequest;
import com.eventpro.dto.response.PromotionResponse;
import com.eventpro.service.PromotionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;


    @GetMapping
    public ResponseEntity<List<PromotionResponse>> recupererToutesLesPromotions() {
        return ResponseEntity.ok(
                promotionService.recupererToutesLesPromotions()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponse> recupererPromotionParId(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                promotionService.recupererPromotionParId(id)
        );
    }

    @PostMapping
    public ResponseEntity<PromotionResponse> creerPromotion(
            @Valid @RequestBody PromotionRequest request) {
         System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        PromotionResponse response =
                promotionService.creerPromotion(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PromotionResponse> modifierPromotion(
//            @PathVariable UUID id,
//            @Valid @RequestBody PromotionRequest request) {
//
//        return ResponseEntity.ok(
//                promotionService.modifierPromotion(id, request)
//        );
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> supprimerPromotion(
//            @PathVariable UUID id) {
//
//        promotionService.supprimerPromotion(id);
//
//        return ResponseEntity.noContent().build();
//    }
}
