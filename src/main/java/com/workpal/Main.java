package com.workpal;

import com.workpal.config.DatabaseConnection;
import com.workpal.dao.impl.OrganisateurDAOImpl;
import com.workpal.dao.impl.PersonneDAOImpl;
import com.workpal.dao.impl.WorkingSpaceDAOImpl;
import com.workpal.dao.interfaces.OrganisateurDAO;
import com.workpal.dao.interfaces.PersonneDAO;
import com.workpal.models.Membre;
import com.workpal.repository.impl.MembreRepositoryImpl;
import com.workpal.repository.impl.OrganisateurRepositoryImpl;
import com.workpal.repository.impl.PersonneRepositoryImpl;
import com.workpal.repository.impl.WorkingSpaceRepositoryImpl;
import com.workpal.repository.interfaces.OrganisateurRepository;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Impl.OrganisateurServiceImpl;
import com.workpal.services.Impl.PersonneServiceImpl;
import com.workpal.services.Impl.WorkingSpaceServiceImpl;
import com.workpal.services.Interfaces.OrganisateurService;
import com.workpal.services.Interfaces.WorkingSpaceService;
import com.workpal.view.admin.OrganisateurView;
import com.workpal.view.auth.RegisterView;
import com.workpal.view.workingSpace.WorkingSpaceView;
import com.workpal.view.workpal.HomeView;
import java.sql.Connection;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        DatabaseConnection.run();
//       RegisterView registerView = new RegisterView();
//        LoginView loginView = new LoginView();
//        loginView.displayLoginMenu();
//        registerView.handleUserRegistration();



//        Membre membre = new Membre(9, "John Doe", "john@example.com", "password", 1, "123456789");
//
//
//        HomeView homeView = new HomeView();
//
//        homeView.updateMembreInfo(membre);
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Obtenez la connexion à partir de DatabaseConnection
        Connection connection = dbConnection.getConnection();
        // Création des DAO
        OrganisateurDAO organisateurDAO = new OrganisateurDAOImpl();
        PersonneDAO personneDAO = new PersonneDAOImpl();

        // Création des Repositories
        OrganisateurRepository organisateurRepository = new OrganisateurRepositoryImpl(organisateurDAO);
        PersonneRepository personneRepository = new PersonneRepositoryImpl(personneDAO);

        // Création du Service
        OrganisateurService organisateurService = new OrganisateurServiceImpl(organisateurRepository, personneRepository);

        // Création de la Vue
        OrganisateurView organisateurView = new OrganisateurView(organisateurService);
        organisateurView.addOrganisateur("testemail", "organisat@gmail.com", "password123",2);


        // Initialiser les DAO, Repository, et Service
        WorkingSpaceDAOImpl workingSpaceDAO = new WorkingSpaceDAOImpl(connection);
        WorkingSpaceRepositoryImpl workingSpaceRepository = new WorkingSpaceRepositoryImpl(workingSpaceDAO);
        WorkingSpaceService workingSpaceService = new WorkingSpaceServiceImpl(workingSpaceRepository);

        // Initialiser la vue
        WorkingSpaceView workingSpaceView = new WorkingSpaceView(workingSpaceService);

        // Menu pour l'interaction utilisateur
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Menu Gestion des Espaces de Travail ---");
            System.out.println("1. Ajouter un nouvel espace de travail");
            System.out.println("2. Afficher les espaces de travail");
            System.out.println("3. Mettre à jour un espace de travail");
            System.out.println("4. Supprimer un espace de travail");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Nettoyer le buffer après la lecture du choix

            switch (choice) {
                case 1:
                    workingSpaceView.addWorkingSpace();
                    break;
                case 2:
                    workingSpaceView.showWorkingSpaces();
                    break;
                case 3:
                    workingSpaceView.updateWorkingSpace();
                    break;
                case 4:
                    workingSpaceView.deleteWorkingSpace();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
            }
        }

        scanner.close();
    }
}


