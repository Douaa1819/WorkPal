package com.workpal.repositories;

import com.workpal.dao.impl.RoleDAOImpl;
import com.workpal.models.Role;

public class RoleRepository {
    private RoleDAOImpl roleDAO = new RoleDAOImpl();

    public Role getRoleByName(String name) {
        return roleDAO.findByName(name);
    }
}
