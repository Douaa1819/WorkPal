package com.workpal.models;


public class Favoris {
    private int membreId;
    private int workingSpaceId;

    public Favoris(int membreId, int workingSpaceId) {
        this.membreId = membreId;
        this.workingSpaceId = workingSpaceId;
    }

    public int getMembreId() {
        return membreId;
    }

    public void setMembreId(int membreId) {
        this.membreId = membreId;
    }

    public int getWorkingSpaceId() {
        return workingSpaceId;
    }

    public void setWorkingSpaceId(int workingSpaceId) {
        this.workingSpaceId = workingSpaceId;
    }
}
