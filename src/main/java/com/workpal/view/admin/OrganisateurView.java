package com.workpal.view.admin;

import com.workpal.models.Organisateur;
import com.workpal.services.Interfaces.OrganisateurService;

import java.util.Scanner;

public class OrganisateurView {
    private final OrganisateurService organisateurService;
    private final Scanner scanner = new Scanner(System.in);
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

    public void deleteOrganisateur() {
        System.out.print("Entrez l'ID de l'organisateur à supprimer : ");
        int id = scanner.nextInt();
        organisateurService.deleteOrganisateur(id);
    }
}


