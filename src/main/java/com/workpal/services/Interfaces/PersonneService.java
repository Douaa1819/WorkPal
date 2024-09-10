package com.workpal.services.Interfaces;

import com.workpal.models.Membre;
import com.workpal.models.Gestionnaire;
import com.workpal.exceptions.InvalidInputException;

public interface PersonneService {

    void registerMembre(Membre membre) throws InvalidInputException;
    void registerGestionnaire(Gestionnaire gestionnaire) throws InvalidInputException;
    void login(String email, String password);
}