package com.workpal.dao.interfaces;

import com.workpal.models.WorkingSpace;

import java.util.List;

public interface WorkingSpaceDAO {

    void save(WorkingSpace workingSpace);

    WorkingSpace findById(int id);

    ;

    List<WorkingSpace> findAll();

    void update(WorkingSpace workingSpace);

    void delete(int id);
}
