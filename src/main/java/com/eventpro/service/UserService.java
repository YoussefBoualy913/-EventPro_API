
package com.eventpro.service;

import com.eventpro.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import com.eventpro.entity.Utilisateur;
import com.eventpro.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UtilisateurRepository utilisateurRepository;

    @Transactional(readOnly = true)
    public List<Utilisateur> recupererTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Utilisateur recupererUtilisateurParId(UUID id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "Utilisateur introuvable : " + id
                        ));
    }


    @Transactional(readOnly = true)
    public boolean utilisateurExiste(UUID id) {
        return utilisateurRepository.existsById(id);
    }

    public void supprimerUtilisateur(UUID id) {
        Utilisateur utilisateur = recupererUtilisateurParId(id);
        utilisateurRepository.delete(utilisateur);
    }
}
