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
        // Ici vous pouvez ajouter des vérifications de validation (ex: nom unique)
        workingSpaceRepository.register(workingSpace);
    }


    @Override
    public WorkingSpace getWorkingSpace(int id) {
        return workingSpaceRepository.getById(id);
    }

    @Override
    public List<WorkingSpace> getAllWorkingSpaces() {
        return workingSpaceRepository.getAll();
    }

    @Override
    public void removeWorkingSpace(int id) {
        workingSpaceRepository.delete(id);
    }

    @Override
    public void updateWorkingSpace(WorkingSpace workingSpace) {
        workingSpaceRepository.update(workingSpace);
    }
}
