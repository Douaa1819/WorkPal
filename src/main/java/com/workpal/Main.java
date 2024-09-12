package com.workpal;

import com.workpal.config.DatabaseConnection;
import com.workpal.dao.impl.PersonneDAOImpl;
import com.workpal.models.Membre;
import com.workpal.repository.impl.MembreRepositoryImpl;
import com.workpal.services.Impl.PersonneServiceImpl;
import com.workpal.view.auth.LoginView;
import com.workpal.view.auth.RegisterView;
import com.workpal.view.workpal.HomeView;

import java.sql.Connection;


public class Main {


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        DatabaseConnection.run();
       RegisterView registerView = new RegisterView();
//        LoginView loginView = new LoginView();
//        loginView.displayLoginMenu();
        registerView.handleUserRegistration();



//        Membre membre = new Membre(9, "John Doe", "john@example.com", "password", 1, "123456789");
//
//
//        HomeView homeView = new HomeView();
//
//        homeView.updateMembreInfo(membre);
        }

    }
