package com.workpal.dao.interfaces;

import com.workpal.exceptions.InvalidInputException;
import com.workpal.models.Gestionnaire;
import com.workpal.models.Membre;
import com.workpal.models.Personne;

import java.util.Optional;

public interface PersonneDAO {
    void registerMembre(Membre membre) throws InvalidInputException;
    void registerGestionnaire(Gestionnaire gestionnaire) throws InvalidInputException;
    Optional<Personne> login(String email, String password);
    Optional<Personne> findByEmail(String email);
}
