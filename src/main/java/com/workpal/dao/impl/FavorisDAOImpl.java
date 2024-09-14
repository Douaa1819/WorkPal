package com.workpal.dao.impl;

import com.workpal.dao.interfaces.FavorisDAO;
import com.workpal.models.Favoris;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FavorisDAOImpl implements FavorisDAO {
    private final Connection connection;

    public FavorisDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Favoris favoris) {
        try {
            String sql = "INSERT INTO favoris (membre_id, working_space_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, favoris.getMembreId());
            stmt.setInt(2, favoris.getWorkingSpaceId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int membreId, int workingSpaceId) {
        try {
            String sql = "DELETE FROM favoris WHERE membre_id = ? AND working_space_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, membreId);
            stmt.setInt(2, workingSpaceId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Favoris> findByMembreId(int membreId) {
        List<Favoris> favorisList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM favoris WHERE membre_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, membreId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Favoris favoris = new Favoris(rs.getInt("membre_id"), rs.getInt("working_space_id"));
                favorisList.add(favoris);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favorisList;
    }
}
