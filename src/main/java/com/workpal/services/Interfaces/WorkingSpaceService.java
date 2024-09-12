package com.workpal.services.Interfaces;

import com.workpal.models.WorkingSpace;

import java.util.List;

public interface WorkingSpaceService {
    void createWorkingSpace(WorkingSpace workingSpace);
    List<WorkingSpace> getAllWorkingSpaces();
    WorkingSpace getWorkingSpaceById(int id);
    void updateWorkingSpace(WorkingSpace workingSpace);
    void deleteWorkingSpace(int id);
}
