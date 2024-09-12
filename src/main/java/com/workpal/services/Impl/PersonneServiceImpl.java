package com.workpal.services.Impl;

import com.workpal.dao.interfaces.AdminDAO;
import com.workpal.dao.interfaces.GestionnareDAO;
import com.workpal.dao.interfaces.MembreDAO;
import com.workpal.models.Personne;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Interfaces.PersonneService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class PersonneServiceImpl implements PersonneService {
    private final PersonneRepository personneRepository;
    private final MembreDAO membreDAO;
    private final GestionnareDAO gestionnaireDAO;
    private final AdminDAO adminDAO;

    // Constructor for a single repository
    public PersonneServiceImpl(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
        this.membreDAO = null;
        this.gestionnaireDAO = null;
        this.adminDAO = null;
    }

    // Constructor for multiple DAOs
    public PersonneServiceImpl(PersonneRepository personneRepository, MembreDAO membreDAO, GestionnareDAO gestionnaireDAO, AdminDAO adminDAO) {
        this.personneRepository = personneRepository;
        this.membreDAO = membreDAO;
        this.gestionnaireDAO = gestionnaireDAO;
        this.adminDAO = adminDAO;
    }

    @Override
    public Optional<Personne> login(String email, String password) {
        Optional<Personne> personneOpt = personneRepository.findByEmail(email);
        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();
            if (BCrypt.checkpw(password, personne.getPassword())) {
                return Optional.of(personne);
            }
        }
        return Optional.empty();
    }

    @Override
    public void resetPassword(String email) {
        Optional<Personne> personneOpt = personneRepository.findByEmail(email);
        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();
            String temporaryPassword = "Temp1234";
            String hashedPassword = BCrypt.hashpw(temporaryPassword, BCrypt.gensalt());
            personne.setPassword(hashedPassword);
            personneRepository.updatePassword(personne);
            System.out.println("Votre nouveau mot de passe temporaire est : " + temporaryPassword);
        } else {
            System.out.println("Email introuvable.");
        }
    }

    @Override
    public boolean isEmailExists(String email) {
        return (personneRepository != null && personneRepository.findByEmail(email).isPresent()) ||
                (membreDAO != null && membreDAO.findByEmail(email).isPresent()) ||
                (gestionnaireDAO != null && gestionnaireDAO.findByEmail(email).isPresent()) ||
                (adminDAO != null && adminDAO.findByEmail(email).isPresent());
    }
}
