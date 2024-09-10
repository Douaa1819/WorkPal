package com.workpal.models;

public class Permission {
    private int id;
    private String permissionName;

    public Permission(int id, String permissionName) {
        this.id = id;
        this.permissionName = permissionName;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
