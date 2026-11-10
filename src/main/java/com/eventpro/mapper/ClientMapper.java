package com.eventpro.mapper;

import com.eventpro.dto.request.ClientRequest;
import com.eventpro.dto.response.ClientResponse;
import com.eventpro.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Client toEntity(ClientRequest request);

    ClientResponse toResponse(Client client);
}
