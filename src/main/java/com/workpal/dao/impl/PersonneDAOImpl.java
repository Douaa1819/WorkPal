package com.workpal.dao.impl;

import com.workpal.config.DatabaseConnection;
import com.workpal.dao.interfaces.PersonneDAO;
import com.workpal.models.Personne;
import com.workpal.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PersonneDAOImpl implements PersonneDAO {
    private Connection connection;

    public PersonneDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Optional<Personne> findByEmail(String email) {
        String query = "SELECT * FROM personne WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Personne personne = new Personne(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("role_id")
                    );
                    return Optional.of(personne);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void updatePassword(Personne personne) {
        String query = "UPDATE personne SET password = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, personne.getPassword());
            statement.setString(2, personne.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
        }
    }
}
