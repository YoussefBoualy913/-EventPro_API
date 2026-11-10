package com.eventpro.mapper;

import com.eventpro.dto.request.AgentControleRequest;
import com.eventpro.dto.response.AgentControleResponse;
import com.eventpro.entity.AgentControle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgentControleMapper {

    @Mapping(target = "id", ignore = true)
    AgentControle toEntity(AgentControleRequest request);

    AgentControleResponse toResponse(AgentControle agentControle);
}
