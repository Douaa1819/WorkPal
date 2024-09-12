package com.workpal.services.Impl;

import com.workpal.models.Membre;
import com.workpal.repository.interfaces.MembreRepository;
import com.workpal.repository.impl.MembreRepositoryImpl;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Interfaces.MembreService;
import org.mindrot.jbcrypt.BCrypt;
public class MembreServiceImpl implements MembreService {


        private final MembreRepository membreRepository;
        private final PersonneRepository personneRepository;

        public MembreServiceImpl(MembreRepository membreRepository, PersonneRepository personneRepository) {
            this.membreRepository = membreRepository;
            this.personneRepository = personneRepository;
        }

        @Override
        public void registerMembre(Membre membre) {
            if (membre == null) {
                throw new IllegalArgumentException("Membre cannot be null");
            }

            if (isEmailExists(membre.getEmail())) {
                throw new IllegalArgumentException("L'email est déjà utilisé.");
            }

            String hashedPassword = BCrypt.hashpw(membre.getPassword(), BCrypt.gensalt());
            membre.setPassword(hashedPassword);

            membreRepository.register(membre);
            System.out.println("Membre enregistré avec succès : " + membre.getName());
        }

        private boolean isEmailExists(String email) {
            return personneRepository.findByEmail(email).isPresent() ||
                    membreRepository.findByEmail(email).isPresent();
        }

    @Override
    public void updateMembre(Membre membre) {
        if (membre == null || membre.getEmail() == null || membre.getPhone() == null) {
            throw new IllegalArgumentException("Les informations du membre sont invalides.");
        }

        membreRepository.updateMembre(membre);
    }

}
