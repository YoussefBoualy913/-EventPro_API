package com.eventpro.mapper;

import com.eventpro.dto.request.RemboursementRequest;
import com.eventpro.dto.response.RemboursementResponse;
import com.eventpro.entity.Remboursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RemboursementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paiement", ignore = true)
    Remboursement toEntity(RemboursementRequest request);

    @Mapping(target = "paiementId", source = "paiement.id")
    @Mapping(target = "referencePaiement", source = "paiement.reference")
    RemboursementResponse toResponse(Remboursement remboursement);
}
