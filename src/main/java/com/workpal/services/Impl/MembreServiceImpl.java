package com.workpal.services.Impl;

import com.workpal.models.Membre;
import com.workpal.repository.interfaces.MembreRepository;
import com.workpal.repository.impl.MembreRepositoryImpl;
import com.workpal.services.Interfaces.MembreService;
import org.mindrot.jbcrypt.BCrypt;
public class MembreServiceImpl implements MembreService {

    private MembreRepository membreRepository = new MembreRepositoryImpl();
    @Override

    public void registerMembre(Membre membre) {
        if (membre == null) {
            throw new IllegalArgumentException("Membre cannot be null");
        }
        if (membreRepository.findByEmail(membre.getEmail()).isPresent()) {
            throw new IllegalArgumentException("L'email est déjà utilisé.");
        }
        String hashedPassword = BCrypt.hashpw(membre.getPassword(), BCrypt.gensalt());
        membre.setPassword(hashedPassword);
        membreRepository.register(membre);
        System.out.println("Membre enregistré avec succès : " + membre.getName());
    }


    @Override
    public void updateMembre(Membre membre) {
        if (membre == null || membre.getEmail() == null || membre.getPhone() == null) {
            throw new IllegalArgumentException("Les informations du membre sont invalides.");
        }

        membreRepository.updateMembre(membre);
    }

}
