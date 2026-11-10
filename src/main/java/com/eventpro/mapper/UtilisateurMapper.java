package com.eventpro.mapper;

import com.eventpro.dto.response.UtilisateurResponse;
import com.eventpro.entity.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurResponse toResponse(Utilisateur utilisateur);
}
