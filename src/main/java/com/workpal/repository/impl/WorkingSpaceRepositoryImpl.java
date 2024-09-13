package com.workpal.repository.impl;

import com.workpal.dao.interfaces.WorkingSpaceDAO;
import com.workpal.models.WorkingSpace;
import com.workpal.repository.interfaces.WorkingSpaceRepository;

import java.util.List;

public class WorkingSpaceRepositoryImpl implements WorkingSpaceRepository {
    private WorkingSpaceDAO workingSpaceDAO;

    public WorkingSpaceRepositoryImpl(WorkingSpaceDAO workingSpaceDAO) {
        this.workingSpaceDAO = workingSpaceDAO;
    }

    @Override
    public void register(WorkingSpace workingSpace) {
        workingSpaceDAO.save(workingSpace);
    }

    @Override
    public WorkingSpace getById(int id) {
        return workingSpaceDAO.findById(id);
    }

    @Override
    public List<WorkingSpace> getAll() {
        return workingSpaceDAO.findAll();
    }

    @Override
    public void delete(int id) {
        workingSpaceDAO.delete(id);
    }


    @Override
    public void update(WorkingSpace workingSpace) {
        workingSpaceDAO.update(workingSpace);
    }
}
