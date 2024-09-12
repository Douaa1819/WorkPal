package com.workpal.dao.impl;

import com.workpal.dao.interfaces.AdminDAO;
import com.workpal.models.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdminDAOImpl implements AdminDAO {
    private final Connection connection;

    public AdminDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        String query = "SELECT * FROM admin WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                );
                return Optional.of(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
