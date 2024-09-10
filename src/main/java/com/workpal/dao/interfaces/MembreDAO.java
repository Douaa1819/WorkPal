package com.workpal.dao.interfaces;

import com.workpal.models.Membre;

import java.util.Optional;


public interface MembreDAO {

    void save(Membre membre);
    Optional<Membre> findByEmail(String email);

}
