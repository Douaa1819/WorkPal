package com.workpal.dao.interfaces;

import com.workpal.models.Favoris;

import java.util.List;


    public interface FavorisDAO {
        void save(Favoris favoris);
        void delete(int membreId, int workingSpaceId);
        List<Favoris> findByMembreId(int membreId);
    }
