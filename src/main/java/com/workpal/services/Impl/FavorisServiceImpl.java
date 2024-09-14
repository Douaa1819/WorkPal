package com.workpal.services.Impl;

import com.workpal.dao.impl.FavorisDAOImpl;
import com.workpal.dao.interfaces.FavorisDAO;
import com.workpal.models.Favoris;
import com.workpal.services.Interfaces.FavorisService;

import java.util.List;

public class FavorisServiceImpl implements FavorisService {

    private final FavorisDAO favorisDAO;

    public FavorisServiceImpl(FavorisDAO favorisDAO) {
        this.favorisDAO = favorisDAO;
    }

    @Override

    public void ajouterFavori(int membreId, int workingSpaceId) {
        Favoris favoris = new Favoris(membreId, workingSpaceId);
        favorisDAO.save(favoris);
    }
    @Override
    public void retirerFavori(int membreId, int workingSpaceId) {
        favorisDAO.delete(membreId, workingSpaceId);
    }


    @Override
    public List<Favoris> obtenirFavorisParMembre(int membreId) {
        return favorisDAO.findByMembreId(membreId);
    }
}
