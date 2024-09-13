package com.workpal.repository.interfaces;

import com.workpal.models.WorkingSpace;

import java.util.List;

public interface WorkingSpaceRepository {
    void register(WorkingSpace workingSpace);
    WorkingSpace getById(int id);
    List<WorkingSpace> getAll();
    void delete(int id);
    void update(WorkingSpace workingSpace);
}
