package com.workpal.services.Interfaces;

import com.workpal.models.Membre;
import com.workpal.models.Gestionnaire;
import com.workpal.exceptions.InvalidInputException;
import com.workpal.models.Personne;

import java.util.Optional;

public interface PersonneService {

    Optional<Personne> login(String email, String password);
    boolean isEmailExists(String email);
    void resetPassword(String email);
}