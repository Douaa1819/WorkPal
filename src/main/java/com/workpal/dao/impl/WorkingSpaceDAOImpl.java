package com.workpal.dao.impl;

import com.workpal.dao.interfaces.WorkingSpaceDAO;
import com.workpal.models.WorkingSpace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingSpaceDAOImpl implements WorkingSpaceDAO {

    private Connection connection;

    public WorkingSpaceDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createWorkingSpace(WorkingSpace workingSpace) {
        String query = "INSERT INTO working_spaces (name, description, manager_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, workingSpace.getName());
            stmt.setString(2, workingSpace.getDescription());
            stmt.setInt(3, workingSpace.getManagerId());
            stmt.executeUpdate();
            System.out.println("Working Space créé avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WorkingSpace getWorkingSpaceById(int id) {
        String query = "SELECT * FROM working_spaces WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new WorkingSpace(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("manager_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<WorkingSpace> getAllWorkingSpaces() {
        List<WorkingSpace> workingSpaces = new ArrayList<>();
        String query = "SELECT * FROM working_spaces";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                workingSpaces.add(new WorkingSpace(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("manager_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workingSpaces;
    }


    @Override
    public void updateWorkingSpace(WorkingSpace workingSpace) {
        String query = "UPDATE working_spaces SET name = ?, description = ?, manager_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, workingSpace.getName());
            stmt.setString(2, workingSpace.getDescription());
            stmt.setInt(3, workingSpace.getManagerId());
            stmt.setInt(4, workingSpace.getId());
            stmt.executeUpdate();
            System.out.println("Working Space mis à jour avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWorkingSpace(int id) {
        String query = "DELETE FROM working_spaces WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Working Space supprimé avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
