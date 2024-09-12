package com.workpal.repository.impl;

import com.workpal.dao.interfaces.WorkingSpaceDAO;
import com.workpal.models.WorkingSpace;
import com.workpal.repository.interfaces.WorkingSpaceRepository;

import java.util.List;

public class WorkingSpaceRepositoryImpl implements WorkingSpaceRepository {

    private final WorkingSpaceDAO workingSpaceDAO;

    public WorkingSpaceRepositoryImpl(WorkingSpaceDAO workingSpaceDAO) {
        this.workingSpaceDAO = workingSpaceDAO;
    }

    @Override
    public void createWorkingSpace(WorkingSpace workingSpace) {
        workingSpaceDAO.createWorkingSpace(workingSpace);
    }

    @Override
    public List<WorkingSpace> getAllWorkingSpaces() {
        return workingSpaceDAO.getAllWorkingSpaces();
    }

    @Override
    public WorkingSpace getWorkingSpaceById(int id) {
        return workingSpaceDAO.getWorkingSpaceById(id);
    }

    @Override
    public void updateWorkingSpace(WorkingSpace workingSpace) {
        workingSpaceDAO.updateWorkingSpace(workingSpace);
    }

    @Override
    public void deleteWorkingSpace(int id) {
        workingSpaceDAO.deleteWorkingSpace(id);
    }
}
