package com.workpal.repository.impl;

import com.workpal.dao.interfaces.PersonneDAO;
import com.workpal.models.Personne;
import com.workpal.repository.interfaces.PersonneRepository;

import java.util.Optional;

public class PersonneRepositoryImpl implements PersonneRepository {

    private final PersonneDAO personneDAO;

    public PersonneRepositoryImpl(PersonneDAO personneDAO) {
        this.personneDAO = personneDAO;
    }

    @Override
    public Optional<Personne> findByEmail(String email) {
        return personneDAO.findByEmail(email);
    }


    @Override
    public void updatePassword(Personne personne) {
        personneDAO.updatePassword(personne);
    }
}
