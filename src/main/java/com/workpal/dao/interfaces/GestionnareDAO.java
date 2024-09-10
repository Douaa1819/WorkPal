package com.workpal.dao.interfaces;

import com.workpal.models.Gestionnaire;

import java.util.Optional;

public interface GestionnareDAO {

    void save(Gestionnaire gestionnaire);
    Optional<Gestionnaire>findByEmail(String email);
}
