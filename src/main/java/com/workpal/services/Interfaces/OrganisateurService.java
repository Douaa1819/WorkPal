package com.workpal.services.Interfaces;

import com.workpal.models.Organisateur;

public interface OrganisateurService {

    void registerOrganisateur(Organisateur organisateur);
    void deleteOrganisateur(int id);
}
