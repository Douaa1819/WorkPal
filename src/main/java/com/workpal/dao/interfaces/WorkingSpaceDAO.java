package com.workpal.dao.interfaces;

import com.workpal.models.WorkingSpace;

import java.util.List;

public interface WorkingSpaceDAO  {

    void createWorkingSpace(WorkingSpace workingSpace);
    WorkingSpace getWorkingSpaceById(int id);
    List<WorkingSpace> getAllWorkingSpaces();
    void updateWorkingSpace(WorkingSpace workingSpace);
    void deleteWorkingSpace(int id);
}
