package com.workpal.dao.impl;

import com.workpal.dao.interfaces.RoleDAO;
import com.workpal.models.Role;
import com.workpal.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoleDAOImpl implements RoleDAO {
    private Connection connection;

    public RoleDAOImpl(

    ) {
        this.connection = connection;
    }

    @Override
    public Role findByName(String name) {
        Role role = null;
        try {
            String sql = "SELECT * FROM roles WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                role = new Role(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
}
