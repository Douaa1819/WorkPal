package com.workpal.services.Interfaces;

import com.workpal.models.WorkingSpace;

import java.util.List;

public interface WorkingSpaceService {
    void createWorkingSpace(WorkingSpace workingSpace);
    WorkingSpace getWorkingSpace(int id);
    List<WorkingSpace> getAllWorkingSpaces();
    void removeWorkingSpace(int id);
    void updateWorkingSpace(WorkingSpace workingSpace);
}
