package com.eventpro.mapper;

import com.eventpro.dto.request.LigneReservationRequest;
import com.eventpro.dto.response.LigneReservationResponse;
import com.eventpro.entity.LigneReservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LigneReservationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "ticketCategoriesPerEvent", ignore = true)
    LigneReservation toEntity(LigneReservationRequest request);

    @Mapping(target = "ticketCategoriesPerEventId", source = "ticketCategoriesPerEvent.id")
    @Mapping(target = "nomCategorie", source = "ticketCategoriesPerEvent.ticketCategory.name")
    LigneReservationResponse toResponse(LigneReservation ligneReservation);
}
