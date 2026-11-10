
package com.eventpro.service;

import com.eventpro.exception.OrganisateurNotFoundException;
import lombok.RequiredArgsConstructor;
import com.eventpro.dto.request.OrganisateurRequest;
import com.eventpro.dto.response.OrganisateurResponse;
import com.eventpro.entity.Organisateur;
import com.eventpro.mapper.OrganisateurMapper;
import com.eventpro.repository.OrganisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganisateurService {

    private final OrganisateurRepository organisateurRepository;
    private final OrganisateurMapper organisateurMapper;


    public OrganisateurResponse creerOrganisateur(
            OrganisateurRequest request) {

        Organisateur organisateur =
                organisateurMapper.toEntity(request);

        Organisateur organisateurEnregistre =
                organisateurRepository.save(organisateur);

        return organisateurMapper.toResponse(organisateurEnregistre);
    }

    @Transactional(readOnly = true)
    public OrganisateurResponse recupererOrganisateurParId(UUID id) {

        Organisateur organisateur =
                organisateurRepository.findById(id)
                        .orElseThrow(() ->
                                new OrganisateurNotFoundException(
                                        "Organisateur introuvable : " + id
                                ));

        return organisateurMapper.toResponse(organisateur);
    }
}
