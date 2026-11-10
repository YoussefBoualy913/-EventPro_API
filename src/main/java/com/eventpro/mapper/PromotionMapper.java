package com.eventpro.mapper;

import com.eventpro.dto.request.PromotionRequest;
import com.eventpro.dto.response.PromotionResponse;
import com.eventpro.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evenement", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    Promotion toEntity(PromotionRequest request);

    @Mapping(target = "evenementId", source = "evenement.id")
    @Mapping(target = "titreEvenement", source = "evenement.titre")
    PromotionResponse toResponse(Promotion promotion);
}
