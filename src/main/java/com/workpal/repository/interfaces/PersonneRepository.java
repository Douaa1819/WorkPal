package com.workpal.repositories.interfaces;

import com.workpal.models.Personne;
import java.util.Optional;

public interface PersonneRepository<T extends Personne> {
    void save(T personne);
    Optional<T> findByEmail(String email);
}
