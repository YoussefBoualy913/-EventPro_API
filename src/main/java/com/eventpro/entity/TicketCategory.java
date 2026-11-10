package com.eventpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;


    @OneToMany(mappedBy = "ticketCategory", cascade = CascadeType.ALL)
    private List<TicketCategoriesPerEvent> ticketCategoriesPerEvents = new ArrayList<>();


}