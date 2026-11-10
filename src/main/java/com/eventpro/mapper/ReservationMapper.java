package com.eventpro.mapper;

import com.eventpro.dto.request.ReservationRequest;
import com.eventpro.dto.response.ReservationResponse;
import com.eventpro.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = LigneReservationMapper.class)
public interface ReservationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "promotion", ignore = true)
    @Mapping(target = "paiement", ignore = true)
    Reservation toEntity(ReservationRequest request);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "nomClient", source = "client.nom")
    @Mapping(target = "emailClient", source = "client.email")
    @Mapping(target = "promotionId", source = "promotion.id")
    @Mapping(target = "codePromotion", source = "promotion.code")
    ReservationResponse toResponse(Reservation reservation);
}
