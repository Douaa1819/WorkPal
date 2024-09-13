package com.workpal.view.admin;

import com.workpal.models.Organisateur;
import com.workpal.services.Interfaces.OrganisateurService;

public class OrganisateurView {
    private final OrganisateurService organisateurService;

    public OrganisateurView(OrganisateurService organisateurService) {
        this.organisateurService = organisateurService;
    }

    public void addOrganisateur(String name, String email, String password, int roleId) {
        Organisateur organisateur = new Organisateur(0, name, email, password, roleId);
        try {
            organisateurService.registerOrganisateur(organisateur);
            System.out.println("Organisateur ajouté avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
