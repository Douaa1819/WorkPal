package com.workpal.repository.impl;

import com.workpal.dao.interfaces.MembreDAO;
import com.workpal.dao.impl.MembreDAOImpl;
import com.workpal.models.Membre;
import com.workpal.repository.interfaces.MembreRepository;

import java.util.Optional;

public class MembreRepositoryImpl implements MembreRepository {

    private MembreDAO membreDAO = new MembreDAOImpl()
            ;

    @Override
    public void register(Membre membre) {
        membreDAO.save(membre);
    }

    @Override
    public Optional<Membre> findByEmail(String email) {
        return membreDAO.findByEmail(email);
    }


    @Override
    public void updateMembre(Membre membre) {
        membreDAO.updateMembre(membre);
    }
}
