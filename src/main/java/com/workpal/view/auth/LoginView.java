package com.workpal.view.auth;

import com.workpal.dao.impl.PersonneDAOImpl;
import com.workpal.repository.impl.PersonneRepositoryImpl;
import com.workpal.services.Impl.PersonneServiceImpl;
import com.workpal.services.Interfaces.PersonneService;

import java.util.Scanner;

public class LoginView {

    private final PersonneService personneService;

    public LoginView() {
        // DAO et Repository initialisés ici
        PersonneDAOImpl personneDAO = new PersonneDAOImpl();
        PersonneRepositoryImpl personneRepository = new PersonneRepositoryImpl(personneDAO);
        this.personneService = new PersonneServiceImpl(personneRepository);
    }

    public void displayLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Login");
        System.out.println("2. Mot de passe oublié");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne

        if (choice == 1) {
            handleLogin(scanner);
        } else if (choice == 2) {
            handlePasswordReset(scanner);
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private void handleLogin(Scanner scanner) {
        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();

        System.out.print("Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        var personneOpt = personneService.login(email, password);
        if (personneOpt.isPresent()) {
            var personne = personneOpt.get();
            switch (personne.getRoleId()) {
                case 1:
                    System.out.println("Bienvenue Membre : " + personne.getName());
                    break;
                case 2:
                    System.out.println("Bienvenue Gestionnaire : " + personne.getName());
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

    private void handlePasswordReset(Scanner scanner) {
        System.out.print("Entrez votre email pour réinitialiser le mot de passe : ");
        String email = scanner.nextLine();
        personneService.resetPassword(email);
    }
}
