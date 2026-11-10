package com.eventpro.mapper;

import com.eventpro.dto.request.OrganisateurRequest;
import com.eventpro.dto.response.OrganisateurResponse;
import com.eventpro.entity.Organisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganisateurMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evenements", ignore = true)
    Organisateur toEntity(OrganisateurRequest request);

    OrganisateurResponse toResponse(Organisateur organisateur);
}
