package com.workpal.dao.impl;

import com.workpal.config.DatabaseConnection;
import com.workpal.dao.interfaces.MembreDAO;
import com.workpal.exceptions.DatabaseException;
import com.workpal.models.Membre;

import java.sql.*;
import java.util.Optional;

public class MembreDAOImpl implements MembreDAO {

    private Connection connection;

    public MembreDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void save(Membre membre) {
        String query = "INSERT INTO membre (name, email, password, phone, role_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, membre.getName());
            statement.setString(2, membre.getEmail());
            statement.setString(3, membre.getPassword());
            statement.setString(4, membre.getPhone());
            statement.setInt(5, membre.getRoleId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        membre.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de l'enregistrement du membre : " + e.getMessage());
        }
    }

    @Override
    public Optional<Membre> findByEmail(String email) {
        String query = "SELECT * FROM membre WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Membre membre = new Membre(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("role_id"),
                            rs.getString("phone")
                    );
                    return Optional.of(membre);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la recherche du membre : " + e.getMessage());
        }
        return Optional.empty();
    }


    @Override
    public void updateMembre(Membre membre) {
        String sql = "UPDATE membre  SET email = ?, phone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, membre.getEmail());
            stmt.setString(2, membre.getPhone());
            stmt.setInt(3, membre.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Échec de la mise à jour du membre, aucun enregistrement trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erreur lors de la mise à jour des informations du membre.", e);
        }
    }
}
