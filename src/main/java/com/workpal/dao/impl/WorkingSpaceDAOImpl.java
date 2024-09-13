package com.workpal.dao.impl;

import com.workpal.dao.interfaces.WorkingSpaceDAO;
import com.workpal.models.WorkingSpace;
import com.workpal.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingSpaceDAOImpl implements WorkingSpaceDAO {
    private Connection connection;

    public WorkingSpaceDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(WorkingSpace workingSpace) throws DatabaseException {
        String query = "INSERT INTO working_space (name, description, manager_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, workingSpace.getName());
            statement.setString(2, workingSpace.getDescription());
            statement.setInt(3, workingSpace.getManagerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de l'enregistrement du WorkingSpace: " + e.getMessage());
        }
    }

    @Override
    public WorkingSpace findById(int id) throws DatabaseException {
        String query = "SELECT * FROM working_space WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new WorkingSpace(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("manager_id"));
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la récupération du WorkingSpace: " + e.getMessage());
        }
    }

    @Override
    public List<WorkingSpace> findAll() throws DatabaseException {
        String query = "SELECT * FROM working_space";
        List<WorkingSpace> spaces = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                spaces.add(new WorkingSpace(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("manager_id")));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la récupération des WorkingSpaces: " + e.getMessage());
        }
        return spaces;
    }


    @Override
    public void update(WorkingSpace workingSpace) throws DatabaseException {
        String query = "UPDATE working_space SET name = ?, description = ?, manager_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, workingSpace.getName());
            statement.setString(2, workingSpace.getDescription());
            statement.setInt(3, workingSpace.getManagerId());
            statement.setInt(4, workingSpace.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la mise à jour du WorkingSpace: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DatabaseException {
        String query = "DELETE FROM wworking_space WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la suppression du WorkingSpace: " + e.getMessage());
        }
    }
}
