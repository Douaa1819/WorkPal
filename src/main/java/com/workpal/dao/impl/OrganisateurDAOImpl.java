package com.workpal.dao.impl;

import com.workpal.config.DatabaseConnection;
import com.workpal.dao.interfaces.OrganisateurDAO;
import com.workpal.exceptions.DatabaseException;
import com.workpal.models.Membre;
import com.workpal.models.Organisateur;
import java.sql.*;
import  java.sql.SQLException;
import java.util.Optional;

public class OrganisateurDAOImpl implements OrganisateurDAO {

    private final Connection connection;

    public OrganisateurDAOImpl() throws DatabaseException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void save(Organisateur organisateur) throws DatabaseException {
        String query = "INSERT INTO organisateur (name, email, password, role_id) VALUES (?, ?,  ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, organisateur.getName());
            statement.setString(2, organisateur.getEmail());
            statement.setString(3, organisateur.getPassword());
            statement.setInt(4, organisateur.getRoleId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected != 1) {
                throw new DatabaseException("Erreur lors de l'enregistrement de l'organisateur.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                organisateur.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de l'enregistrement de l'organisateur : " + e.getMessage());
        }
    }


    @Override
    public Optional<Organisateur> findByEmail(String email) {
        String query = "SELECT * FROM organisateur WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Organisateur organisateur = new Organisateur(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("role_id")
                            );
                    return Optional.of(organisateur);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la recherche du organisateur : " + e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public void delete(int id) throws DatabaseException {
        String query = "DELETE FROM organisateur WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected != 1) {
                throw new DatabaseException("Erreur lors de la suppression de l'organisateur.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la suppression de l'organisateur : " + e.getMessage());
        }
    }
}
