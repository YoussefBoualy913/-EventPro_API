package com.eventpro.mapper;

import com.eventpro.dto.request.EvenementRequest ;
import com.eventpro.dto.response.EvenementResponse;
import com.eventpro.entity.Evenement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EvenementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organisateur", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "promotions", ignore = true)
    Evenement toEntity(EvenementRequest  request);

    @Mapping(target = "organisateurId", source = "organisateur.id")
    @Mapping(target = "nomOrganisateur", source = "organisateur.nom")
    EvenementResponse toResponse(Evenement evenement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organisateur", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "promotions", ignore = true)
    void updateEvenementFromDTO(
            EvenementRequest request,
            @MappingTarget Evenement evenement
    );
}
