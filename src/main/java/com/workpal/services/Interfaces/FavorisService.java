package com.workpal.services.Interfaces;

import com.workpal.models.Favoris;

import java.util.List;

public interface FavorisService {
    void ajouterFavori(int membreId, int workingSpaceId);
    void retirerFavori(int membreId, int workingSpaceId);
    List<Favoris> obtenirFavorisParMembre(int membreId);
}


