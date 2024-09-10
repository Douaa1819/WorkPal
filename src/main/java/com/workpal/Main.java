package com.workpal;

import com.workpal.config.DatabaseConnection;
import com.workpal.view.RegisterView;


public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        DatabaseConnection.run();


        RegisterView registerView = new RegisterView();
        registerView.registerMembre();
        }
    }
