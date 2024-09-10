package com.workpal.repository.interfaces;

import com.workpal.models.Personne;
import java.util.Optional;

public interface PersonneRepository<T extends Personne> {
    Optional<Personne> findByEmail(String email);
    void updatePassword(Personne personne);
}
