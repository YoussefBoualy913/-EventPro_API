package com.eventpro.mapper;

import com.eventpro.dto.request.AdministrateurRequest;
import com.eventpro.dto.response.AdministrateurResponse;
import com.eventpro.entity.Administrateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdministrateurMapper {

    @Mapping(target = "id", ignore = true)
    Administrateur toEntity(AdministrateurRequest request);

    AdministrateurResponse toResponse(Administrateur administrateur);
}
