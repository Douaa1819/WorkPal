package com.workpal.view.workpal;
import com.workpal.dao.impl.PersonneDAOImpl;
import com.workpal.dao.interfaces.PersonneDAO;
import com.workpal.models.Membre;
import com.workpal.repository.impl.MembreRepositoryImpl;
import com.workpal.repository.impl.PersonneRepositoryImpl;
import com.workpal.repository.interfaces.MembreRepository;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Interfaces.MembreService;
import com.workpal.services.Impl.MembreServiceImpl;

import java.util.Scanner;

public class HomeView {

    private MembreService membreService;

    public HomeView() {
        MembreRepository membreRepository = new MembreRepositoryImpl();
        PersonneDAO personneDAO = new PersonneDAOImpl();
        PersonneRepository personneRepository = new PersonneRepositoryImpl(personneDAO);
        this.membreService = new MembreServiceImpl(membreRepository, personneRepository);
    }

    public void updateMembreInfo(Membre membre) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Que souhaitez-vous modifier?");
        System.out.println("1. Modifier l'email");
        System.out.println("2. Modifier le téléphone");
        System.out.println("3. Modifier les deux");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Nouvel email : ");
                String email = scanner.nextLine();
                membre.setEmail(email);
                break;
            case 2:
                System.out.print("Nouveau numéro de téléphone : ");
                String phone = scanner.nextLine();
                membre.setPhone(phone);
                break;
            case 3:
                System.out.print("Nouvel email : ");
                email = scanner.nextLine();
                membre.setEmail(email);

                System.out.print("Nouveau numéro de téléphone : ");
                phone = scanner.nextLine();
                membre.setPhone(phone);
                break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        try {
            membreService.updateMembre(membre);
            System.out.println("Informations mises à jour avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}