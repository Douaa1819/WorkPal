package com.workpal.services.Interfaces;

import com.workpal.models.Personne;

import java.util.Optional;

public interface PersonneService {

    Optional<Personne> login(String email, String password);
    boolean isEmailExists(String email);
    void resetPassword(String email);
}