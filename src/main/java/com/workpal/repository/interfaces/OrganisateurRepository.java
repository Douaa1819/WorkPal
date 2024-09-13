package com.workpal.repository.interfaces;

import com.workpal.models.Organisateur;

import java.util.Optional;

public interface OrganisateurRepository {

    void register(Organisateur organisateur);
    Optional<Organisateur> findByEmail(String email);
}
