package com.workpal.repository.impl;

import com.workpal.dao.impl.OrganisateurDAOImpl;
import com.workpal.dao.interfaces.OrganisateurDAO;
import com.workpal.models.Membre;
import com.workpal.models.Organisateur;
import com.workpal.repository.interfaces.OrganisateurRepository;

import java.util.Optional;

public class  OrganisateurRepositoryImpl implements OrganisateurRepository {

    private final OrganisateurDAO organisateurDAO;
    public OrganisateurRepositoryImpl(OrganisateurDAO organisateurDAO) {
        this.organisateurDAO = organisateurDAO;
    }
    @Override
    public void register(Organisateur organisateur) {
        organisateurDAO.save(organisateur);
    }

    public Optional<Organisateur >findByEmail(String email) {
        return organisateurDAO.findByEmail(email);
    }

    @Override
    public void delete(int id) {
        organisateurDAO.delete(id);
    }
}
