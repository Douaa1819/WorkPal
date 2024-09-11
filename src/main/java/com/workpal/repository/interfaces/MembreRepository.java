package com.workpal.repository.interfaces;

import com.workpal.models.Membre;

import java.util.Optional;

public interface MembreRepository {
    void register(Membre membre);
    Optional<Membre> findByEmail(String email);
    void updateMembre(Membre membre);
}

