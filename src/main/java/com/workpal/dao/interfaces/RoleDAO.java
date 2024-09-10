package com.workpal.dao.interfaces;

import com.workpal.models.Personne;
import com.workpal.models.Role;

public interface RoleDAO {
    Role findByName(String name);
}
