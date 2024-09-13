package com.workpal.view.auth;

import com.workpal.dao.impl.PersonneDAOImpl;
import com.workpal.dao.interfaces.PersonneDAO;
import com.workpal.exceptions.InvalidInputException;
import com.workpal.models.Membre;
import com.workpal.repository.impl.MembreRepositoryImpl;
import com.workpal.repository.impl.PersonneRepositoryImpl;
import com.workpal.repository.interfaces.MembreRepository;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Impl.MembreServiceImpl;
import com.workpal.utils.ValidateUser;
import com.workpal.view.workpal.HomeView;

import java.util.Scanner;

public class RegisterView {
    private final MembreServiceImpl membreService;
    private final Scanner scanner = new Scanner(System.in);
    private final HomeView homeView;

    public RegisterView() {
        PersonneDAO personneDAO = new PersonneDAOImpl();
        PersonneRepository personneRepository = new PersonneRepositoryImpl(personneDAO);
        MembreRepository membreRepository = new MembreRepositoryImpl();
        this.membreService = new MembreServiceImpl(membreRepository, personneRepository);
        this.homeView = new HomeView();
    }

    public void registerMembre() {
        try {
            Membre membre = createMembreFromInput();
            membreService.registerMembre(membre);
            System.out.println("Membre inscrit avec succès.");
            homeView.displayMembreMenu();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
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
