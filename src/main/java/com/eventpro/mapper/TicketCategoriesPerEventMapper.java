package com.eventpro.mapper;

import com.eventpro.dto.request.TicketCategoriesPerEventRequest;
import com.eventpro.dto.response.TicketCategoriesPerEventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketCategoriesPerEventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evenement", ignore = true)
    @Mapping(target = "ticketCategory", ignore = true)
    @Mapping(target = "ligneReservations", ignore = true)
    com.eventpro.entity.TicketCategoriesPerEvent toEntity(TicketCategoriesPerEventRequest request);

    @Mapping(target = "evenementId", source = "evenement.id")
    @Mapping(target = "ticketCategoryId", source = "ticketCategory.id")
    @Mapping(target = "titreEvenement", source = "evenement.titre")
    TicketCategoriesPerEventResponse toResponse(com.eventpro.entity.TicketCategoriesPerEvent TicketCategoriesPerEvent);
}
