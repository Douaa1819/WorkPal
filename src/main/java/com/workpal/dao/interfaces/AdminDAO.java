package com.workpal.dao.interfaces;

import com.workpal.models.Admin;

import java.util.Optional;

public interface AdminDAO {

    Optional<Admin> findByEmail(String email);
}
