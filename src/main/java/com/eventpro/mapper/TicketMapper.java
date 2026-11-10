package com.eventpro.mapper;

import com.eventpro.dto.request.TicketRequest;
import com.eventpro.dto.response.TicketResponse;
import com.eventpro.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ligneReservation", ignore = true)
    Ticket toEntity(TicketRequest request);

    @Mapping(target = "ligneReservationId", source = "ligneReservation.id")
    @Mapping(target = "nomCategorie", source = "ligneReservation.ticketCategoriesPerEvent.ticketCategory.name")
    @Mapping(target = "prixUnitaire", source = "ligneReservation.prixUnitaire")
    TicketResponse toResponse(Ticket ticket);
}
