package com.workpal.services.Impl;

import com.workpal.models.Membre;
import com.workpal.models.Organisateur;
import com.workpal.repository.interfaces.OrganisateurRepository;
import com.workpal.repository.interfaces.PersonneRepository;
import com.workpal.services.Interfaces.OrganisateurService;
import org.mindrot.jbcrypt.BCrypt;

public class OrganisateurServiceImpl implements OrganisateurService {

    private final OrganisateurRepository organisateurRepository;
    private final PersonneRepository personneRepository;

    public OrganisateurServiceImpl(OrganisateurRepository organisateurRepository, PersonneRepository personneRepository) {
        this.organisateurRepository = organisateurRepository;
        this.personneRepository = personneRepository;
    }

    @Override
    public void registerOrganisateur(Organisateur organisateur) {
        if (organisateur == null) {
            throw new IllegalArgumentException("Organisateur peut pas null");
        }

        if (isEmailExists(organisateur.getEmail())) {
            throw new IllegalArgumentException("L'email est déjà utilisé.");
        }

        String hashedPassword = BCrypt.hashpw(organisateur.getPassword(), BCrypt.gensalt());
        organisateur.setPassword(hashedPassword);

        organisateurRepository.register(organisateur);
        System.out.println("organisateur enregistré avec succès : " + organisateur.getName());

    }

    private boolean isEmailExists(String email) {
        return personneRepository.findByEmail(email).isPresent() ||
                organisateurRepository.findByEmail(email).isPresent();
    }

    @Override
    public void deleteOrganisateur(int id) {
        organisateurRepository.delete(id);
        System.out.println("Organisateur supprimé avec succès : " + id);
    }

}
