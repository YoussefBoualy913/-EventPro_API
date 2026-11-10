package com.eventpro.mapper;

import com.eventpro.dto.request.PaiementRequest;
import com.eventpro.dto.response.PaiementResponse;
import com.eventpro.entity.Paiement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "remboursements", ignore = true)
    Paiement toEntity(PaiementRequest request);

    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "referenceReservation", source = "reservation.reference")
    PaiementResponse toResponse(Paiement paiement);
}
