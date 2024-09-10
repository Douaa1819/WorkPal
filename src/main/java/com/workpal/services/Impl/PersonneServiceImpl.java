package com.workpal.services.Impl;

import com.workpal.models.Personne;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Interfaces.PersonneService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class PersonneServiceImpl implements PersonneService {
    private final PersonneRepository personneRepository;

    public PersonneServiceImpl(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
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
}
