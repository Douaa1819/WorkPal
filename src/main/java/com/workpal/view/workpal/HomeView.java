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
    private final Scanner scanner = new Scanner(System.in);

    public HomeView() {
        MembreRepository membreRepository = new MembreRepositoryImpl();
        PersonneDAO personneDAO = new PersonneDAOImpl();
        PersonneRepository personneRepository = new PersonneRepositoryImpl(personneDAO);
        this.membreService = new MembreServiceImpl(membreRepository, personneRepository);
    }

    public void updateMembreInfo(Membre membre) {
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

    public void displayMembreMenu() {
        while (true) {
            System.out.println("=== Espace Membre ===");
            System.out.println("1. Modifier les informations personnelles");
            System.out.println("2. Gestion des réservations");
            System.out.println("3. Rechercher des espaces disponibles");
            System.out.println("4. Sauvegarder des espaces favoris");
            System.out.println("5. Consulter un calendrier des événements");
            System.out.println("6. Quitter");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Veuillez entrer les informations du membre à modifier.");
                    // Simulate user input for demonstration purposes
                    // In practice, you would fetch this from a database or user input
                    System.out.print("ID : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nom : ");
                    String name = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String password = scanner.nextLine();
                    scanner.nextLine();
                    System.out.print("Téléphone : ");
                    String phone = scanner.nextLine();

                    // Create a new Membre object with user input
                    Membre membre = new Membre(id, name, email, password, 1, phone);
                    updateMembreInfo(membre);
                    break;
                case 2:
                    System.out.println("Gestion des réservations");
                    break;
                case 3:
                    System.out.println("Recherche d'espaces disponibles");
                    break;
                case 4:
                    System.out.println("Sauvegarde des espaces favoris");
                    break;
                case 5:
                    System.out.println("Consultation du calendrier des événements");
                    break;
                case 6:
                    System.out.println("Sortie du programme.");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

}
