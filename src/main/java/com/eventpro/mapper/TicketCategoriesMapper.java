package com.eventpro.mapper;

import com.eventpro.dto.request.TicketCategoriesRequest;
import com.eventpro.dto.response.TicketCategoriesResponse;
import com.eventpro.entity.TicketCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketCategoriesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ticketCategoriesPerEvents", ignore = true)
    TicketCategory toEntity(TicketCategoriesRequest request);

    TicketCategoriesResponse toResponse(TicketCategory category);
}