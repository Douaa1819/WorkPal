package com.workpal.view.auth;

import com.workpal.dao.impl.PersonneDAOImpl;
import com.workpal.dao.interfaces.PersonneDAO;
import com.workpal.exceptions.InvalidInputException;
import com.workpal.models.Membre;
import com.workpal.repository.impl.MembreRepositoryImpl;
import com.workpal.repository.impl.PersonneRepositoryImpl;
import com.workpal.repository.interfaces.MembreRepository;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Interfaces.MembreService;
import com.workpal.services.Impl.MembreServiceImpl;
import com.workpal.utils.ValidateUser;

import java.util.Scanner;

public class RegisterView {

    private final MembreService membreService;
    private final Scanner scanner = new Scanner(System.in);

    public RegisterView() {
        MembreRepository membreRepository = new MembreRepositoryImpl();
        PersonneDAO personneDAO = new PersonneDAOImpl();
        PersonneRepository personneRepository = new PersonneRepositoryImpl(personneDAO);
        this.membreService = new MembreServiceImpl(membreRepository, personneRepository);
    }

    public void registerMembre() {
        boolean isValidInput = false;
        Membre membre = null;

        while (!isValidInput) {
            try {
                membre = createMembreFromInput();
                isValidInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println("Veuillez réessayer avec des informations valides.");
            }
        }

        try {
            membreService.registerMembre(membre);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private Membre createMembreFromInput() throws InvalidInputException {
        System.out.print("Entrez votre nom : ");
        String name = scanner.nextLine();
        ValidateUser.validateName(name);

        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        ValidateUser.validateEmail(email);

        System.out.print("Entrez votre mot de passe : ");
        String password = scanner.nextLine();
        ValidateUser.validatePassword(password);

        System.out.print("Entrez votre numéro de téléphone : ");
        String phone = scanner.nextLine();
        ValidateUser.validatePhone(phone);

        int role_id = 1;

        return new Membre(0, name, email, password, role_id, phone);
    }

    public void handleUserRegistration() {
        boolean success = false;

        while (!success) {
            try {
                Membre membre = createMembreFromInput();
                try {
                    membreService.registerMembre(membre);
                    success = true;
                } catch (IllegalArgumentException e) {
                    System.err.println("Erreur : " + e.getMessage());
                    System.out.print("Voulez-vous réessayer (R) ou quitter (Q) ? ");
                    String choice = scanner.nextLine().trim().toUpperCase();

                    if (choice.equals("Q")) {
                        System.out.println("Programme terminé.");
                        System.exit(0);
                    } else if (!choice.equals("R")) {
                        System.out.println("Option invalide. Programme terminé.");
                        System.exit(1);
                    }
                }
            } catch (InvalidInputException e) {
                System.out.println("Erreur d'entrée : " + e.getMessage());
                System.out.println("Veuillez réessayer avec des informations valides.");
            }
        }
    }
}
