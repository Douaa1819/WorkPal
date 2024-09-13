package com.workpal.dao.interfaces;

import com.workpal.models.Personne;

import java.util.Optional;

public interface PersonneDAO {

    Optional<Personne> findByEmail(String email);
    void updatePassword(Personne personne);
}
