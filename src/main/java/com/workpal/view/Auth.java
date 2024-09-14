package com.workpal.view;

import com.workpal.view.auth.LoginView;
import com.workpal.view.auth.RegisterView;

import java.util.Scanner;

public class Auth {
    private final Scanner scanner = new Scanner(System.in);
    private final LoginView loginView;
    private final RegisterView registerView;

    public Auth() {
        this.loginView = new LoginView();
        this.registerView = new RegisterView();
    }

    public void auth() {
        System.out.println(Email.Mail("rabiilfarakh2816@gmail.com"));
        System.out.println("1. Login");
        System.out.println("2. Register");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            loginView.displayLoginMenu();
        } else if (choice == 2) {
            registerView.registerMembre();
        } else {
            System.out.println("Choix invalide.");
        }
    }
}
