package com.eventpro.mapper;

import com.eventpro.dto.request.EvenementRequest ;
import com.eventpro.dto.response.EvenementResponse;
import com.eventpro.entity.Evenement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvenementMapper {

    @Mapping(target = "id", ignore = true)
    Evenement toEntity(EvenementRequest  request);

    EvenementResponse toResponse(Evenement evenement);
}