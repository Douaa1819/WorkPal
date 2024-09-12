package com.workpal.repository.interfaces;

import com.workpal.models.WorkingSpace;

import java.util.List;

public interface WorkingSpaceRepository {
    void createWorkingSpace(WorkingSpace workingSpace);
    List<WorkingSpace> getAllWorkingSpaces();
    WorkingSpace getWorkingSpaceById(int id);
    void updateWorkingSpace(WorkingSpace workingSpace);
    void deleteWorkingSpace(int id);
}
