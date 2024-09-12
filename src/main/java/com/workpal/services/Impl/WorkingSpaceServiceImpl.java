package com.workpal.services.Impl;


import com.workpal.models.WorkingSpace;
import com.workpal.repository.interfaces.WorkingSpaceRepository;
import com.workpal.services.Interfaces.WorkingSpaceService;

import java.util.List;

public class WorkingSpaceServiceImpl implements WorkingSpaceService {

    private final WorkingSpaceRepository workingSpaceRepository;

    public WorkingSpaceServiceImpl(WorkingSpaceRepository workingSpaceRepository) {
        this.workingSpaceRepository = workingSpaceRepository;
    }

    @Override
    public void createWorkingSpace(WorkingSpace workingSpace) {
        workingSpaceRepository.createWorkingSpace(workingSpace);
    }

    @Override
    public List<WorkingSpace> getAllWorkingSpaces() {
        return workingSpaceRepository.getAllWorkingSpaces();
    }

    @Override
    public WorkingSpace getWorkingSpaceById(int id) {
        return workingSpaceRepository.getWorkingSpaceById(id);
    }

    @Override
    public void updateWorkingSpace(WorkingSpace workingSpace) {
        workingSpaceRepository.updateWorkingSpace(workingSpace);
    }

    @Override
    public void deleteWorkingSpace(int id) {
        workingSpaceRepository.deleteWorkingSpace(id);
    }
}
