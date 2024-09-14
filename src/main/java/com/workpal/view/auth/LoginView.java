package com.workpal.view.auth;

import com.workpal.config.DatabaseConnection;
import com.workpal.dao.impl.AdminDAOImpl;
import com.workpal.dao.impl.MembreDAOImpl;
import com.workpal.dao.impl.OrganisateurDAOImpl;
import com.workpal.dao.impl.PersonneDAOImpl;
import com.workpal.dao.impl.WorkingSpaceDAOImpl; // Add this import
import com.workpal.dao.interfaces.WorkingSpaceDAO;
import com.workpal.services.Interfaces.FavorisService; // Import FavorisService
import com.workpal.dao.impl.FavorisDAOImpl; // Import FavorisDAO
import com.workpal.models.Personne;
import com.workpal.repository.impl.PersonneRepositoryImpl;
import com.workpal.repository.impl.WorkingSpaceRepositoryImpl; // Add this import
import com.workpal.repository.interfaces.WorkingSpaceRepository;
import com.workpal.services.Impl.PersonneServiceImpl;
import com.workpal.services.Impl.WorkingSpaceServiceImpl;
import com.workpal.services.Interfaces.WorkingSpaceService;
import com.workpal.view.workingSpace.WorkingSpaceView;
import com.workpal.view.workpal.HomeView;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;

public class LoginView {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    private final PersonneDAOImpl personneDAO = new PersonneDAOImpl();
    private final PersonneRepositoryImpl personneRepository = new PersonneRepositoryImpl(personneDAO);
    private final MembreDAOImpl membreDAO = new MembreDAOImpl();
    private final OrganisateurDAOImpl organisateurDAO = new OrganisateurDAOImpl();
    private final AdminDAOImpl adminDAO = new AdminDAOImpl(connection);

    private final PersonneServiceImpl personneService = new PersonneServiceImpl(personneRepository, membreDAO, organisateurDAO, adminDAO);
    private final Scanner scanner = new Scanner(System.in);

    private final WorkingSpaceView workingSpaceView;
    private final HomeView homeView;

    public LoginView() {
        WorkingSpaceDAO workingSpaceDAO = new WorkingSpaceDAOImpl(connection); // Create the DAO instance
        WorkingSpaceRepository workingSpaceRepository = new WorkingSpaceRepositoryImpl(workingSpaceDAO); // Pass the DAO
        WorkingSpaceService workingSpaceService = new WorkingSpaceServiceImpl(workingSpaceRepository); // Pass the repository
        this.homeView = new HomeView();
        this.workingSpaceView = new WorkingSpaceView(workingSpaceService);
    }

    // Menu de connexion
    public void displayLoginMenu() {
        System.out.println("1. Login");
        System.out.println("2. Mot de passe oublié");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            handleLogin();
        } else if (choice == 2) {
            handlePasswordReset();
        } else {
            System.out.println("Choix invalide.");
        }
    }

    // Gérer la connexion
    private void handleLogin() {
        System.out.print(" Entrer Votre Email : ");
        String email = scanner.nextLine();

        System.out.print(" Entrer Votre Mot de passe : ");
        String password = scanner.nextLine();

        Optional<Personne> personneOpt = personneService.login(email, password);

        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();

            switch (personne.getRoleId()) {
                case 1:
                    System.out.println("Bienvenue Membre : " + personne.getName());
                    homeView.displayMembreMenu(); // Pass the Personne object
                    break;
                case 2:
                    System.out.println("Bienvenue Gestionnaire : " + personne.getName());
                    workingSpaceView.displayWorkingSpaceMenu();
                    break;
                case 3:
                    System.out.println("Bienvenue Admin !");
                    break;
                default:
                    System.out.println("Rôle non reconnu.");
            }
        } else {
            System.out.println("Email ou mot de passe incorrect.");
        }
    }

    private void handlePasswordReset() {
        System.out.print("Email : ");
        String email = scanner.nextLine();
        personneService.resetPassword(email);
    }
}
