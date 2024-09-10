package com.workpal.view;

import com.workpal.exceptions.InvalidInputException;
import com.workpal.models.Membre;
import com.workpal.services.Interfaces.MembreService;
import com.workpal.services.Impl.MembreServiceImpl;
import com.workpal.utils.ValidateUser;

import java.util.Scanner;

public class RegisterView {

    private MembreService membreService = new MembreServiceImpl();
    private Scanner scanner = new Scanner(System.in);

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
}