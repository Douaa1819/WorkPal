package com.workpal.dao.interfaces;

import com.workpal.models.Organisateur;

import java.util.Optional;

public interface OrganisateurDAO {

    void save(Organisateur organisateur);
    Optional<Organisateur>findByEmail(String email);
    void delete(int id);
}
